package com.rmit.sept.majorProject.service;

import java.util.ArrayList;
import java.util.Optional;
import com.rmit.sept.majorProject.dto.AdminSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.model.Person.Role;
import com.rmit.sept.majorProject.repository.AdminRepository;

@Service
public class AdminService implements PersonService<Admin> {

	@Autowired
	private AdminRepository repository;
	@Autowired
	private DuplicateCheckService duplicateCheck;

	//--------ADMIN-SPECIFIC FUNCTIONS----------

	public Admin registerNewAdmin(final Admin admin) throws DuplicateKeyException {
		String username = admin.getUsername();
		if(duplicateCheck.usernameExists(username)) {
            throw new DuplicateKeyException("An account already exists with username: " + username);
		}
		Admin newAdmin = new Admin(admin);
		return repository.save(newAdmin);
	}

	//---------------DTO FUNCTIONS--------------

	public Iterable<AdminSummary> findAllDTO(){
		ArrayList<AdminSummary> adminDtos = new ArrayList<AdminSummary>();
		Iterable<Admin> admins = repository.findAll();
		for(Admin admin : admins){
			adminDtos.add(new AdminSummary(admin));
		}
		return adminDtos;
	}
	
    public AdminSummary findByUsernameDTO(String adminUsername) {
		Admin admin = repository.findByUsername(adminUsername);
		AdminSummary summary = null;
		if(admin == null){
			throw new UsernameNotFoundException("Admin not found in the database");
		} 
		else {
			summary = new AdminSummary(admin);
		}
		return summary;
    }
	
    //---------GENERIC PERSON FUNCTIONS---------

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public void delete(Admin person) {
		repository.delete(person);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public void deleteAll(Iterable<Admin> persons) {
		repository.deleteAll(persons);
	}

	@Override
	public void deleteById(long id) {
		repository.deleteById(id);
	}

	@Override
	public boolean existsById(long id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<Admin> findAll() {
		return repository.findAll();
	}

	@Override
	public Iterable<Admin> findAllById(Iterable<Long> ids) {
		return repository.findAllById(ids);
	}

	@Override
	public Optional<Admin> findById(long id) {
		return repository.findById(id);
	}

	@Override
	public Admin save(Admin person) {
		return repository.save(person);
	}

	@Override
	public Iterable<Admin> saveAll(Iterable<Admin> persons) {
		return repository.saveAll(persons);
	}

	@Override
	public Iterable<Admin> findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Admin findByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public Iterable<Admin> findByRole(Role role) {
		return repository.findByRole(role);
	}
	
}
