package com.rmit.sept.majorProject.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.model.Person;

@Repository
public class AdminRepository implements CrudRepository<Admin, Long>, PersonRepository {

	private List<Person> adminList;

	AdminRepository() {
		this.adminList = new LinkedList<Person>();
	}

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

	@Override
	public <S extends Admin> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Admin> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Admin> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Admin> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Admin> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Admin entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Admin> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}
	

}
