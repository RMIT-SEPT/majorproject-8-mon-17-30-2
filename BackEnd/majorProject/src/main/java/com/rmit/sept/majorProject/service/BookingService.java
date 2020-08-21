package com.rmit.sept.majorProject.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.repository.BookingRepository;
import com.rmit.sept.majorProject.repository.BusinessRepository;
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
	@Autowired
	private BusinessRepository busiRepository;
	
	public Booking createNewBooking(Booking booking)
	{
		if(this.workerService.findByUsername(booking.getWorker().getUsername()) == null 
				|| this.custSevice.findByUsername(booking.getCustomer().getUsername()) == null)
		{
			return null;
		}
		booking.setWorker(this.workerService.findByUsername(booking.getWorker().getUsername()));
		booking.setCustomer(this.custSevice.findByUsername(booking.getCustomer().getUsername()));
		booking.setService(this.servRepository.findByTitle(booking.getService().getTitle()));
		booking.setBusiness(this.busiRepository.findByBusinessName(booking.getBusiness().getBusinessName()));
		if(duplicateBooking(booking))
		{
			return booking;
		}
		return this.repository.save(booking);		
	}
	
	public boolean duplicateBooking(Booking booking)
	{
		for(Booking bookings:getBookingsByCustomer(booking.getCustomer().getUsername()))
		{
			if(bookings.getBusiness().equals(booking.getBusiness()) && 
					bookings.getWorker().equals(booking.getWorker())
					&& bookings.getService().equals(booking.getService())
					&& bookings.getBookingSlot().equals(booking.getBookingSlot())
					)
			{
				return true;
			}
		}
		return false;
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
