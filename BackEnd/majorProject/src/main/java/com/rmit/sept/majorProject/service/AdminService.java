package com.rmit.sept.majorProject.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.model.Person.Role;
import com.rmit.sept.majorProject.repository.AdminRepository;

@Service
public class AdminService implements PersonService<Admin> {

	@Autowired
	private AdminRepository repository;

	//--------ADMIN-SPECIFIC FUNCTIONS----------

	public Admin registerNewAdmin(final Admin admin) throws DuplicateKeyException {
		Admin test = repository.findByUsername(admin.getUsername());
		if(test != null) {
			throw new DuplicateKeyException(("An account already exists with username: " + admin.getUsername()));
		}
		Admin newAdmin = new Admin(admin);
		return repository.save(newAdmin);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Admin person) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<Admin> persons) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean existsById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Admin> findAll() {
		return repository.findAll();
	}

	@Override
	public Iterable<Admin> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Admin> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin save(Admin person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Admin> saveAll(Iterable<Admin> persons) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Admin> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Admin> findByRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//---------GENERIC PERSON FUNCTIONS------------
	
	
}
