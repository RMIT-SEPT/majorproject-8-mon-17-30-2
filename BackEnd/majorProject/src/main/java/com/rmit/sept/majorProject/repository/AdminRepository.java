package com.rmit.sept.majorProject.repository;

import javax.transaction.Transactional;
import com.rmit.sept.majorProject.model.Admin;

@Transactional
public interface AdminRepository extends PersonRepository<Admin> {

	/*
	 * Springboot literally reads the names of these abstract methods and creates an
	 * SQL query from them. Ensure they're named according to the pattern "findByX"
	 * where X is the exact name of an attribute.
	 */

}
