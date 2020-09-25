package com.rmit.sept.majorProject.security;

import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.repository.AdminRepository;
import com.rmit.sept.majorProject.repository.CustomerRepository;
import com.rmit.sept.majorProject.repository.WorkerRepository;
import com.rmit.sept.majorProject.security.AuthenticationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
<<<<<<< HEAD
@CrossOrigin(origins = "http://localhost:3000")
// @CrossOrigin(origins="http://agmemonday2.com.s3-website-us-east-1.amazonaws.com")
=======
import com.rmit.sept.majorProject.Util;

@CrossOrigin(origins = Util.API_HOST)
>>>>>>> develop
@RestController
public class BasicAuthenticationController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping(path = "/auth/{username}/{password}")
    public AuthenticationBean authenticate(@PathVariable String username, @PathVariable String password ) {
        Person person = null;
        Customer customer = customerRepository.findByUsernameAndPassword(username, password);
        Worker worker = workerRepository.findByUsernameAndPassword(username, password);
        Admin admin = adminRepository.findByUsernameAndPassword(username, password);

        if(customer != null){
            person = customer;
        } else if(worker != null) {
            person = worker;
        } else if(admin != null) {
            person = admin;
        } else{
            throw new UsernameNotFoundException("Username does not exist in the database");
        }

        return new AuthenticationBean(person.getRole().toString(), person.getId());
    }

}

