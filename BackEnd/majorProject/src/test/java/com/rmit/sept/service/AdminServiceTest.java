package com.rmit.sept.service;


import com.rmit.sept.majorProject.dto.AdminSummary;
import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.repository.AdminRepository;
import com.rmit.sept.majorProject.service.AdminService;
import com.rmit.sept.majorProject.service.DuplicateCheckService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {AdminRepository.class, AdminService.class, DuplicateCheckService.class})
public class AdminServiceTest {
    @TestConfiguration
    static class AdminServiceImplTestContextConfiguration{
        @Bean
        public AdminService AdminService() {
            return new AdminService();
        }
    }

    @Autowired
    private AdminService adminService;
    @MockBean
    private AdminRepository repository;
    @MockBean
    private DuplicateCheckService duplicateCheck;

    private Admin admin;

    @BeforeEach
    public void setupBeforeEachTest(){

        admin = new Admin("Caramel", "caramel6", "password");
        admin.setId((long) 1);
        Business business = new Business("Mock-Business");
        business.setId((long) 1);
        admin.setBusiness(business);
    }
    @Test
    public void registerNewAdmin_Registration_IfValidAdmin(){

        when(duplicateCheck.usernameExists(admin.getUsername())).thenReturn(false);
        when(repository.save(admin)).thenReturn(admin);

         assertEquals(admin, adminService.registerNewAdmin(admin));
    }

    @Test
    public void registerNewAdmin_ThrowsDuplicateKeyException_IfAdminAlreadyExists(){
        String errorMsg = "An account already exists with username: " + admin.getUsername();
        when(duplicateCheck.usernameExists(admin.getUsername())).thenThrow(new DuplicateKeyException(errorMsg));
        when(repository.save(admin)).thenReturn(admin);

        assertThrows(DuplicateKeyException.class, () -> adminService.registerNewAdmin(admin), errorMsg);
    }

    @Test
    public void findByUsernameDTO_ReturnsAdminDTO_UsernameFound(){
//        Admin admin = repository.findByUsername(adminUsername);
//        AdminSummary summary = null;
//        if(admin == null){
//            throw new UsernameNotFoundException("Admin not found in the database");
//        }
//        else {
//            summary = new AdminSummary(admin);
//        }
//        return summary;
        when(repository.findByUsername(admin.getUsername())).thenReturn(admin);
        assertEquals(new AdminSummary(admin), adminService.findByUsernameDTO(admin.getUsername()));

    }

    @Test
    public void findByUsernameDTO_ThrowsUsernameNotFoundException_UsernameNotFound(){
        String errorMsg = "Admin not found in the database";
        when(repository.findByUsername(admin.getUsername())).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> adminService.findByUsernameDTO(admin.getUsername()), errorMsg);

    }

}
