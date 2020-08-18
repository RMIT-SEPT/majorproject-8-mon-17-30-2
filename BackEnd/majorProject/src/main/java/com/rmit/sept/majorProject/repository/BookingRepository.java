package com.rmit.sept.majorProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rmit.sept.majorProject.model.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long>{
	
	public Iterable<Booking> findByCustomerUsername(String customerUsername);

	public Iterable<Booking> findByWorkerUsername(String workerUsername);

}
