package com.rmit.sept.majorProject.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.model.Worker;

@Repository("workerRepo")
public interface WorkerRepository extends CrudRepository<Worker, Long>, PersonRepository{

	@Override
	Iterable<Worker> findAllById(Iterable<Long> id);
	
	@Override
	Iterable<Worker> findAll();
	
	// @Override
	// public List<Person> getAllPeople() {
	// 	return this.workerList;
	// }

	// @Override
	// public Person getPerson(Long id) {
	// 	for(Person person:this.workerList)
	// 	{
	// 		if(person.getId() == id)
	// 		{
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
