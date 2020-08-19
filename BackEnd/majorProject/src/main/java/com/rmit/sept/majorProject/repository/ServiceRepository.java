package com.rmit.sept.majorProject.repository;

import com.rmit.sept.majorProject.model.Service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long>{
	
    public Service findByTitle(String title);

}