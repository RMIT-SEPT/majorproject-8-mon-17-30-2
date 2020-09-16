package com.rmit.sept.majorProject.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.model.BookingSlot;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Worker;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long>{
	
    @Query(value = "SELECT TOP ?1 * FROM booking ORDER BY id DESC", nativeQuery = true)
    public Iterable<Booking> getNewestParameterised(Integer noBookings);
	
	public Iterable<Booking> findByCustomerUsername(String customerUsername);

	public Iterable<Booking> findByCustomerId(Long customerId);

	public Iterable<Booking> findByWorkerUsername(String workerUsername);

	public Iterable<Booking> findByWorkerId(Long workerId);

	public Iterable<Booking> findByBusiness(Business business);

	public Iterable<Booking> findByWorker(Worker worker);

	public Iterable<Booking> findByBookingSlotDate(LocalDate day);


}
