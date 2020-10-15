package com.rmit.sept.majorProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.model.Person.Role;

@NoRepositoryBean
public interface PersonRepository<T extends Person> extends CrudRepository<T, Long> {

	public Iterable<T> findByName(String name);

	public T findByUsername(String username);

	public T findByUsernameAndPassword(String username, String password);

	public Iterable<T> findByRole(Role role);

}
