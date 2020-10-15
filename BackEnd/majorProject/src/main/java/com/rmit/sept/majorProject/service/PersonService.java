package com.rmit.sept.majorProject.service;

import java.util.Optional;
import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.model.Person.Role;

public interface PersonService<T extends Person> {

	public long count();

	public void delete(T person);

	public void deleteAll();

	public void deleteAll(Iterable<T> persons);

	public void deleteById(long id);

	public boolean existsById(long id);

	public Iterable<T> findAll();

	public Iterable<T> findAllById(Iterable<Long> ids);

	public Optional<T> findById(long id);

	public T save(T person);

	public Iterable<T> saveAll(Iterable<T> persons);

	public Iterable<T> findByName(String name);

	public T findByUsername(String username);

	public Iterable<T> findByRole(Role role);

}
