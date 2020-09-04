package com.rmit.sept.majorProject.service;

import com.rmit.sept.majorProject.dto.BookingSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.model.BookingSlot;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.repository.BookingRepository;
import com.rmit.sept.majorProject.repository.BookingSlotRepository;
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
	@Autowired
	private BookingSlotRepository bookingSlotRepository;
	
	
	public BookingSummary createNewBooking(Booking booking)
	{
		//TODO Retest, maybe modify the equals?
		System.out.println(booking);
		if(this.workerService.findByUsername(booking.getWorker().getUsername()) == null 
				|| this.custSevice.findByUsername(booking.getCustomer().getUsername()) == null){
			 throw new UsernameNotFoundException("worker not passed");
		}
		booking.setWorker(this.workerService.findByUsername(booking.getWorker().getUsername()));
		booking.setCustomer(this.custSevice.findByUsername(booking.getCustomer().getUsername()));
		booking.setService(this.servRepository.findByTitle(booking.getService().getTitle()));
		booking.setBusiness(this.busiRepository.findByBusinessName(booking.getBusiness().getBusinessName()));
//		booking.setBookingSlot(this.bookingSlotRepository.findById(booking.getBookingSlot().getId()).get());
		try {
			for(BookingSlot bookingSlots: bookingSlotRepository.findByDateAndStartTimeAndEndTime(booking.getBookingSlot().getDate(), 
					booking.getBookingSlot().getStartTime(), 
					booking.getBookingSlot().getEndTime()))
			{
				for(com.rmit.sept.majorProject.model.Service service: bookingSlots.getAvailableServices())
				{
					if(booking.getService() == service && !bookingSlots.fullyBooked())
					{
						booking.setBookingSlot(bookingSlots);
						booking.getBookingSlot().setBookedService(service);
					}
				}
			}
		}
		catch(NullPointerException e) {}
		
		System.out.println(booking.getBookingSlot());
		if(duplicateBooking(booking))
		{
			return new BookingSummary(booking);
		}
		return new BookingSummary(this.repository.save(booking));
	}
	
	public boolean duplicateBooking(Booking booking){
		for(Booking bookings:findByCustomerUsername(booking.getCustomer().getUsername())){
			try {
				if(bookings.getBusiness().equals(booking.getBusiness()) && 
						bookings.getWorker().equals(booking.getWorker())
						&& bookings.getService().equals(booking.getService())
						&& bookings.getBookingSlot().equals(booking.getBookingSlot())){
					return true;
				}
			}
			catch(NullPointerException e) {}
			
		}
		return false;
	}
	
	public Iterable<Booking> getAllBookings(){
		return repository.findAll();
	}

	public Iterable<BookingSummary> getAllBookingsDTO(){
		ArrayList<BookingSummary> allBookingDtos = new ArrayList<BookingSummary>();
        for(Booking booking : getAllBookings()){
            allBookingDtos.add(new BookingSummary(booking));
        }
        return allBookingDtos;
	}

	public Iterable<Booking> findByCustomerUsername(String customerUsername){
		return repository.findByCustomerUsername(customerUsername);
	}

	public Iterable<Booking> findByCustomerId(Long customerId){
		return repository.findByCustomerId(customerId);
	}

	public Iterable<BookingSummary> findByCustomerIdDTO(Long customerId){
		ArrayList<BookingSummary> allBookingDtos = new ArrayList<BookingSummary>();
        for(Booking booking : findByCustomerId(customerId)){
            allBookingDtos.add(new BookingSummary(booking));
        }
        return allBookingDtos;
	}

	public Iterable<BookingSummary> getPastBookingsByCustomerIdDTO(Long customerId){
		ArrayList<BookingSummary> pastBookings = new ArrayList<BookingSummary>();
		for(Booking booking : findByCustomerId(customerId)){
			if (booking.getBookingSlot().getBookSlotDate().compareTo(LocalDate.now()) < 0) {
				pastBookings.add(new BookingSummary(booking));
			}
		}
		return pastBookings;
	}

	public Iterable<Booking> getBookingsByWorker(String workerUsername){
		return repository.findByWorkerUsername(workerUsername);
	}
	
	public Iterable<Booking> getAvailableBookingsByBusiness(Business business)
	{
		ArrayList<Booking> bookingList = (ArrayList<Booking>) repository.findByBusiness(business);
		ArrayList<Booking> businessBooking = new ArrayList<Booking>();
		for(Booking booking: bookingList)
		{
			if(booking.getBusiness() == business && booking.getCustomer() == null)
			{
				businessBooking.add(booking);
			}
		}
		return businessBooking;
	}
	
	public Iterable<Booking> getAvailableBookingsByWorker(Worker worker)
	{
		ArrayList<Booking> bookingList = (ArrayList<Booking>) repository.findByWorker(worker);
		ArrayList<Booking> workerBooking = new ArrayList<Booking>();
		for(Booking booking: bookingList)
		{
			if(booking.getWorker() == worker && booking.getCustomer() == null)
			{
				workerBooking.add(booking);
			}
		}
		return workerBooking;
	}
	
	public Iterable<Booking> getAvailableBookingsByDay(LocalDate day)
	{
		ArrayList<Booking> bookingList = (ArrayList<Booking>) repository.findByBookingSlotDate(day);
		ArrayList<Booking> dayBooking = new ArrayList<Booking>();
		for(Booking booking: bookingList)
		{
			if(booking.getBookingSlot().getDate() == day && booking.getCustomer() == null)
			{
				dayBooking.add(booking);
			}
		}
		return dayBooking;
	}
}
