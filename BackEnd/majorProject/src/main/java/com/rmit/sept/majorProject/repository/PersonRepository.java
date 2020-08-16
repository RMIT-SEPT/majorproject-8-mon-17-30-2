package com.rmit.sept.majorProject.repository;

import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.model.Person.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersonRepository<T extends Person> extends CrudRepository<T, Long>{
	
	public Iterable<T> findByName(String name);

	public T findByUsername(String username);

	public Iterable<T> findByRole(Role role);

}
