package com.rmit.sept.security;

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
	}
	
	//Test login worker
	public void testLoginWorker() {
		Worker workerTest = new Worker();
	}
	
	//Test login admin
	public void testLoginAdmin() {
		Admin adminTest = new Admin();
		
	}
}
