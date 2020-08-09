package com.rmit.sept.majorProject.repository;
import com.rmit.sept.majorProject.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Person, Long>, PersonRepository{
	
	@Override
	Iterable<Person> findAllById(Iterable<Long> id);

}