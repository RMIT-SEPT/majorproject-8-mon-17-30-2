package com.rmit.sept.majorProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rmit.sept.majorProject.model.Service;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long> {

    public Service findByTitle(String title);

    public Iterable<Service> findByBusinessId(Long businessId);

}