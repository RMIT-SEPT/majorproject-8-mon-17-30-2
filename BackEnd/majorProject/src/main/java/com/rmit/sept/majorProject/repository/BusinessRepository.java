package com.rmit.sept.majorProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.rmit.sept.majorProject.model.Business;

@Transactional
public interface BusinessRepository extends CrudRepository<Business, Long> {

	Business findByBusinessName(String businessName);

}