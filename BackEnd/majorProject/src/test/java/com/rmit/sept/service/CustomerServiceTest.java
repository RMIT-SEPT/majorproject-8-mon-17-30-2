package com.rmit.sept.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;
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
	
	static Customer customerTest0;
	static Customer customerTest1;
	
	@BeforeAll
	static void init() {
		customerTest0 = new Customer();
		customerTest1 = new Customer("cust","custUser","custPass","custStreet","cust@email.com","3215321");
	}
	
	//Test with no details given (this currently cannot be tested because the check is done at the controller
	@Test
	public void testRegisterNewCustomer_NoDetailsGiven() {
		Customer result;
		when(duplicateCheck.emailExists(customerTest0.getEmail())).thenReturn(false);
		when(duplicateCheck.usernameExists(customerTest0.getUsername())).thenReturn(false);
		when(customerRepository.save(customerTest0)).thenReturn(customerTest0);
		result = customerService.registerNewCustomer(customerTest0);
		assertEquals(customerTest0,result);
	}
	
	//Test with details given
	@Test
	public void testRegisterNewCustomer_DetailsGiven() {
		Customer result;
		when(duplicateCheck.emailExists(customerTest1.getEmail())).thenReturn(false);
		when(duplicateCheck.usernameExists(customerTest1.getUsername())).thenReturn(false);
		when(customerRepository.save(customerTest1)).thenReturn(customerTest1);
		result = customerService.registerNewCustomer(customerTest1);
		assertEquals(customerTest1,result);
	}
	
	//Test customer with duplicate email
	@Test
	public void testRegisterNewCustomer_DuplicateEmail() {
		when(duplicateCheck.emailExists(customerTest1.getEmail())).thenReturn(true);
		when(duplicateCheck.usernameExists(customerTest1.getUsername())).thenReturn(false);
		when(customerRepository.save(customerTest1)).thenReturn(customerTest1);
		 Assertions.assertThrows(DuplicateKeyException.class, () -> {
				customerService.registerNewCustomer(customerTest1);
			  });
	}
	
	//Test customer with duplicate username
	@Test
	public void testRegisterNewCustomer_DuplicateUsername() {
		when(duplicateCheck.emailExists(customerTest1.getEmail())).thenReturn(false);
		when(duplicateCheck.usernameExists(customerTest1.getUsername())).thenReturn(true);
		when(customerRepository.save(customerTest1)).thenReturn(customerTest1);
		 Assertions.assertThrows(DuplicateKeyException.class, () -> {
				customerService.registerNewCustomer(customerTest1);
			  });
	}
	
}
