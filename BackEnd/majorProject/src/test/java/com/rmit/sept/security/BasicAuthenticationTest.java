package com.rmit.sept.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.repository.AdminRepository;
import com.rmit.sept.majorProject.repository.CustomerRepository;
import com.rmit.sept.majorProject.repository.WorkerRepository;
import com.rmit.sept.majorProject.security.AuthenticationBean;
import com.rmit.sept.majorProject.security.BasicAuthenticationController;

@ExtendWith(SpringExtension.class)
public class BasicAuthenticationTest {
	
	@TestConfiguration
	static class BasicAuthenticationImpleTestContextConfiguration{
		@Bean
		public BasicAuthenticationController basicAuthenticationController() {
			return new BasicAuthenticationController();
		}
	}
	
	@Autowired
	private BasicAuthenticationController basicAuthenticationController;
	@MockBean
	private CustomerRepository customerRepository;
	@MockBean
    private WorkerRepository workerRepository;
	@MockBean
    private AdminRepository adminRepository;
	
	//Test login customer
	@Test
	public void testLoginCustomer() {
		Customer customerTest = new Customer("cust","custUser","custPass","custStreet","cust@email.com","3215321");
		when(customerRepository.findByUsernameAndPassword(customerTest.getUsername(), customerTest.getPassword())).thenReturn(customerTest);
		when(workerRepository.findByUsernameAndPassword(customerTest.getUsername(), customerTest.getPassword())).thenReturn(null);
		when(adminRepository.findByUsernameAndPassword(customerTest.getUsername(), customerTest.getPassword())).thenReturn(null);
		AuthenticationBean result = basicAuthenticationController.authenticate(customerTest.getUsername(), customerTest.getPassword());
		assertEquals(customerTest.getRole().name(),result.getRole());
	}
	
	//Test login worker
	@Test
	public void testLoginWorker() {
		Worker workerTest = new Worker("worker","workerUser","workerPass","worker@email.com","0 Worker Street", "123456789");
		when(workerRepository.findByUsernameAndPassword(workerTest.getUsername(), workerTest.getPassword())).thenReturn(workerTest);
		when(customerRepository.findByUsernameAndPassword(workerTest.getUsername(), workerTest.getPassword())).thenReturn(null);
		when(adminRepository.findByUsernameAndPassword(workerTest.getUsername(), workerTest.getPassword())).thenReturn(null);
		AuthenticationBean result = basicAuthenticationController.authenticate(workerTest.getUsername(), workerTest.getPassword());
		assertEquals(workerTest.getRole().name(),result.getRole());
	}
	
	//Test login admin
	@Test
	public void testLoginAdmin() {
		Admin adminTest = new Admin("Admin", "adminUser", "adminPass");
		when(adminRepository.findByUsernameAndPassword(adminTest.getUsername(), adminTest.getPassword())).thenReturn(adminTest);
		when(customerRepository.findByUsernameAndPassword(adminTest.getUsername(), adminTest.getPassword())).thenReturn(null);
		when(workerRepository.findByUsernameAndPassword(adminTest.getUsername(), adminTest.getPassword())).thenReturn(null);
		AuthenticationBean result = basicAuthenticationController.authenticate(adminTest.getUsername(), adminTest.getPassword());
		assertEquals(adminTest.getRole().name(),result.getRole());
		
	}
}
