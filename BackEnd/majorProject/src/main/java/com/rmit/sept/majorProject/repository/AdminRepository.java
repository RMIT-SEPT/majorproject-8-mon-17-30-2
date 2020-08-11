package com.rmit.sept.majorProject.repository;

import com.rmit.sept.majorProject.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.model.Person;

import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long>, PersonRepository {

	@Override
	Iterable<Admin> findAllById(Iterable<Long> id);
	
	@Override
	Iterable<Admin> findAll();

	Admin findAdminByUsername(String username);
	
	// @Override
	// public List<Person> getAllPeople() {
	// 	return this.adminList;
	// }

	// @Override
	// public Person getPerson(Long ID) {
	// 	for (Person person : this.adminList) {
	// 		if (person.getId() == ID) {
	// 			return person;
	// 		}
	// 	}
	// 	return null;
	// }

	// @Override
	// public Boolean addPerson(Person person) {
	// 	// TODO Auto-generated method stub
	// 	return null;
	// }

	// @Override
	// public Boolean deletePerson(Long id) {
	// 	// TODO Auto-generated method stub
	// 	return null;
	// }

	// @Override
	// public Boolean updatePerson(Long id, Person person) {
	// 	// TODO Auto-generated method stub
	// 	return null;
	// }

	

}
