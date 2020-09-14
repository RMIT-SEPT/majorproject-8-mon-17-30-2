package com.rmit.sept.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.repository.AdminRepository;
import com.rmit.sept.majorProject.repository.CustomerRepository;
import com.rmit.sept.majorProject.repository.WorkerRepository;
import com.rmit.sept.majorProject.service.*;
@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {
	
	@TestConfiguration
	static class CustomerServiceImpleTestContextConfiguration{
		@Bean
		public CustomerService customerService() {
			return new CustomerService();
		}
	}
	@Autowired
	private CustomerService customerService;
	@MockBean
	private CustomerRepository customerRepository;
	@MockBean
    private WorkerRepository workerRepository;
	@MockBean
    private AdminRepository adminRepository;
	@MockBean
	private DuplicateCheckService duplicateCheck;
	
	//Test with no details given
	// @Test
	// public void testRegisterNewCustomer0() {
	// 	Customer customerTest = new Customer();
	// 	Customer result;
	// 	try {
	// 		result = customerService.registerNewCustomer(customerTest);
	// 	}
	// 	catch(NullPointerException e) {
	// 		result = null;
	// 	}
	// 	assertNotEquals(customerTest,result);
	// }
	
	//Test with details given
	// @Test
	// public void testRegisterNewCustomer1() {
	// 	Customer customerTest = new Customer("cust","custUser","custPass","custStreet","cust@email.com","3215321");
	// 	Customer result;
	// 	when(duplicateCheck.emailExists(customerTest.getEmail())).thenReturn(false);
	// 	when(duplicateCheck.usernameExists(customerTest.getUsername())).thenReturn(false);
	// 	when(customerRepository.save(customerTest)).thenReturn(customerTest);
	// 	result = customerService.registerNewCustomer(customerTest);
	// 	assertEquals(customerTest,result);
	// }

	@Test
	void check_User_IDs(){
		Customer customerTest = new Customer("cust","custUser","custPass","custStreet","cust@email.com","3215321");
		Customer result;
		 when(duplicateCheck.emailExists(customerTest.getEmail())).thenReturn(false);
		 when(duplicateCheck.usernameExists(customerTest.getUsername())).thenReturn(false);
		 when(customerRepository.save(customerTest)).thenReturn(customerTest);
		result = customerService.registerNewCustomer(customerTest);
		
		assertEquals(result.getId(),customerTest.getId());
	}
	
}
