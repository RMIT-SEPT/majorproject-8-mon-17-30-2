package com.rmit.sept.majorProject.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.repository.BookingRepository;
import com.rmit.sept.majorProject.repository.ServiceRepository;

@Service
public class BookingService{

	@Autowired
	private BookingRepository repository;
	@Autowired
	private WorkerService workerService;
	@Autowired
	private CustomerService custSevice;
	@Autowired
	private ServiceRepository servRepository;
	
	public Booking createNewBooking(Booking booking)
	{
		System.out.println("Hi" + booking.getWorker().getUsername());
		System.out.println("Hi" + this.workerService.findByUsername(booking.getWorker().getUsername()));
		if(this.workerService.findByUsername(booking.getWorker().getUsername()) == null 
				|| this.custSevice.findByUsername(booking.getCustomer().getUsername()) == null)
		{
			return null;
		}
		booking.setWorker(this.workerService.findByUsername(booking.getWorker().getUsername()));
		booking.setCustomer(this.custSevice.findByUsername(booking.getCustomer().getUsername()));
		booking.setService(this.servRepository.findByTitle(booking.getService().getTitle()));
		return this.repository.save(booking);		
	}
	
	public Iterable<Booking> getAllBookings(){
		return repository.findAll();
	}

	public Iterable<Booking> getBookingsByCustomer(String customerUsername){
		return repository.findByCustomerUsername(customerUsername);
	}

	public Iterable<Booking> getBookingsByWorker(String workerUsername){
		return repository.findByWorkerUsername(workerUsername);
	}

}
