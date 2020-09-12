package com.rmit.sept.service;


import com.rmit.sept.majorProject.repository.AdminRepository;
import com.rmit.sept.majorProject.service.AdminService;
import com.rmit.sept.majorProject.service.DuplicateCheckService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {AdminRepository.class, AdminService.class})
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


    @Test
    public void registerNewAdmin_Registration_IfValidAdmin(){

    }

    @Test
    public void registerNewAdmin_ThrowsDuplicateKeyException_IfAdminAlreadyExists(){

    }

    @Test
    public void findByUsernameDTO_ReturnsAdminDTO_UsernameFound(){

    }

    @Test
    public void findByUsernameDTO_ThrowsUsernameNotFoundException_UsernameNotFound(){

    }





}
