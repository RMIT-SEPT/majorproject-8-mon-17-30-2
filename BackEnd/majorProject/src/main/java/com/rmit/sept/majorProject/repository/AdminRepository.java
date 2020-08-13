package com.rmit.sept.majorProject.repository;

import javax.transaction.Transactional;
import com.rmit.sept.majorProject.model.Admin;

@Transactional
public interface AdminRepository extends PersonRepository<Admin>{

	//no special functions required

}
