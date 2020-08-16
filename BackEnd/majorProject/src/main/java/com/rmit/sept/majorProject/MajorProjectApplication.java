package com.rmit.sept.majorProject;

import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.repository.AdminRepository;
import com.rmit.sept.majorProject.repository.CustomerRepository;
import com.rmit.sept.majorProject.repository.WorkerRepository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MajorProjectApplication {

	/*
		Testing login api with database initialisation
	 */
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private WorkerRepository workerRepository;

	public static void main(String[] args) {
		SpringApplication.run(MajorProjectApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			customerRepository.save(new Customer("Austin", "aus", "pass",
												 "address", "customer@bookworm.com", "12345"));
			workerRepository.save(new Worker("John", "john", "pword", "worker@bookworm.com",
											 "address", "12345"));
			adminRepository.save(new Admin("Admin", "caramel6", "password"));
		};
	}

}
