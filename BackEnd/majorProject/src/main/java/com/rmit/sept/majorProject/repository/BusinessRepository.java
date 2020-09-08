package com.rmit.sept.majorProject.repository;

import com.rmit.sept.majorProject.model.Business;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BusinessRepository extends CrudRepository<Business, Long>{

	Business findByBusinessName(String businessName);

	
	
}
