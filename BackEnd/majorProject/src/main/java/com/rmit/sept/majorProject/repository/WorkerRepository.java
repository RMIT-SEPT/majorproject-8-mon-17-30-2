package com.rmit.sept.majorProject.repository;

import javax.transaction.Transactional;
import com.rmit.sept.majorProject.model.Worker;

@Transactional
public interface WorkerRepository extends PersonRepository<Worker>{

	public Worker findByEmail(String email);

	public Worker findByAddress(String address);

	public Worker findByPhoneNumber(String phoneNumber);

}
