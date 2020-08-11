package com.rmit.sept.majorProject.security;

import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.repository.AdminRepository;
import com.rmit.sept.majorProject.repository.CustomerRepository;
import com.rmit.sept.majorProject.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
    UserDetailsServiceImpl is used by SecurityConfiguration to configure
    how spring security authorises users,
    in this case we find the user with their username
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private WorkerRepository workerRepository;

    /*
        search the database to see if the user exists,
        if the user exists we create UserDetails for spring to authorise them
        otherwise we can throw an exception
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = null;
        Customer customer = customerRepository.findCustomerByUsername(username);
        Worker worker = workerRepository.findWorkerByUsername(username);
        Admin admin = adminRepository.findAdminByUsername(username);

        if(customer != null){
            person = customer;
        } else if(worker != null) {
            person = worker;
        } else if(admin != null) {
            person = admin;
        } else{
            throw new UsernameNotFoundException("Username does not exist in the database");
        }

        return new UserDetailsImpl(person);
    }
}
