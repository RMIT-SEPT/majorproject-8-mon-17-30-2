package com.rmit.sept.majorProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.repository.AdminRepository;
import com.rmit.sept.majorProject.repository.CustomerRepository;
import com.rmit.sept.majorProject.repository.WorkerRepository;

@Service
public class DuplicateCheckService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private AdminRepository adminRepository;

    public boolean usernameExists(String username) {
        Customer customer = customerRepository.findByUsername(username);
        Worker worker = workerRepository.findByUsername(username);
        Admin admin = adminRepository.findByUsername(username);
        return (customer != null || worker != null || admin != null);
    }

    public boolean emailExists(String email) {
        Customer customer = customerRepository.findByEmail(email);
        Worker worker = workerRepository.findByEmail(email);
        return (customer != null || worker != null);
    }

}
