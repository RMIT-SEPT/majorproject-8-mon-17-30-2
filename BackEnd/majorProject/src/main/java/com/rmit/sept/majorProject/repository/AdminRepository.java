package com.rmit.sept.majorProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.model.Person;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long>, PersonRepository {

	@Override
	Iterable<Admin> findAllById(Iterable<Long> id);
	
	@Override
	Iterable<Admin> findAll();
	
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
