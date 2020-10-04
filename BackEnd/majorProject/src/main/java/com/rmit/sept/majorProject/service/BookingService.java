package com.rmit.sept.majorProject.service;

import com.rmit.sept.majorProject.dto.BookingBlueprint;
import com.rmit.sept.majorProject.dto.BookingSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.DuplicateKeyException;
import com.rmit.sept.majorProject.model.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.model.Booking.Status;
import com.rmit.sept.majorProject.model.BookingSlot;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.repository.BookingRepository;
import com.rmit.sept.majorProject.repository.BookingSlotRepository;
import com.rmit.sept.majorProject.utility.DateTimeSort;

@org.springframework.stereotype.Service
public class BookingService{

	// repositories
	@Autowired
	private BookingRepository repository;
	@Autowired
	private BookingSlotRepository bookingSlotRepository;

	// services
	@Autowired
	private WorkerService workerService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BusinessService businessService;
	@Autowired
	private ServiceService serviceService;
	@Autowired
	private BookingSlotService bookingSlotService;
	
	
	public BookingSummary createNewBooking(BookingBlueprint blueprint){

		Customer customer = customerService.findById(blueprint.getCustomerId()).get();;
		Worker worker = workerService.findById(blueprint.getWorkerId()).get();
		Business business = businessService.findById(blueprint.getBusinessId());
		Service service = serviceService.findById(blueprint.getServiceId());
		BookingSlot bookingSlot = bookingSlotService.findById(blueprint.getBookingSlotId());

		if(worker == null) {
			throw new DataRetrievalFailureException("Worker not found");			
		}
		if(customer == null) {
			throw new DataRetrievalFailureException("Customer not found");
		}
		if(business == null) {
			throw new DataRetrievalFailureException("Business not found");
		}
		if(service == null) {
			throw new DataRetrievalFailureException("Service not found");
		}
		if(bookingSlot == null) {
			throw new DataRetrievalFailureException("Booking Slot not found");
		}
		if(bookingSlot.fullyBooked()){
			throw new DataIntegrityViolationException("Service is fully booked");
		}
	
		bookingSlotRepository.save(bookingSlot);
		Booking booking = new Booking(customer, worker, business, service, bookingSlot);

		if(duplicateBooking(booking)){
			throw new DuplicateKeyException("This booking already exists");
		}
		
		booking = this.repository.save(booking);
		bookingSlot.addBooking(booking);
		bookingSlotRepository.save(bookingSlot);
		return new BookingSummary(booking);
	}
	
	public boolean duplicateBooking(Booking booking){
		for(Booking bookings:findByCustomerUsername(booking.getCustomer().getUsername())){
			try {
				if(bookings.getBusiness().equals(booking.getBusiness()) && 
						bookings.getWorker().equals(booking.getWorker())
						&& bookings.getService().equals(booking.getService())
						&& bookings.getBookingSlot().equals(booking.getBookingSlot())
						&& booking.getStatus() != Status.CANCELLED){
					return true;
				}
			}
			catch(NullPointerException e) {}
			
		}
		return false;
	}

	public boolean cancelBooking(Long id){
		
		for(Booking booking : getAllBookings()){
			if(id == booking.getBookingId()){
				if(ChronoUnit.DAYS.between(LocalDate.now(),booking.getBookingSlot().getDate()) >= 2)
				{
					booking.setStatusCancelled();
					booking.getBookingSlot().removeBooking(booking);
					bookingSlotRepository.save(booking.getBookingSlot());
					repository.save(booking);
					return true;
				}
				throw new DataIntegrityViolationException("Exceeded booking cancellation time of 48 hours");
			}
        }
		return false;
	}
	
	public Iterable<Booking> getAllBookings(){
		updateBookingStatus();
		return repository.findAll();
	}

	public Iterable<BookingSummary> getAllBookingsDTO(){
		ArrayList<BookingSummary> allBookingDtos = new ArrayList<BookingSummary>();
        for(Booking booking : getAllBookings()){
            allBookingDtos.add(new BookingSummary(booking));
		}
		Collections.sort(allBookingDtos, new DateTimeSort());
		return allBookingDtos;
	}

	public Iterable<Booking> findByCustomerUsername(String customerUsername){
		updateBookingStatus();
		return repository.findByCustomerUsername(customerUsername);
	}

	public Iterable<Booking> findByCustomerId(Long customerId){
		updateBookingStatus();
		return repository.findByCustomerId(customerId);
	}
	public Iterable<Booking> findByBusinessId(Long businessId){
		updateBookingStatus();
		return repository.findByBusinessId(businessId);
	}

	public Iterable<BookingSummary> findByCustomerIdDTO(Long customerId){
		ArrayList<BookingSummary> allBookingDtos = new ArrayList<BookingSummary>();
        for(Booking booking : findByCustomerId(customerId)){
            allBookingDtos.add(new BookingSummary(booking));
		}
		Collections.sort(allBookingDtos, new DateTimeSort());
        return allBookingDtos;
	}

	public Iterable<BookingSummary> getPastBookingsByCustomerIdDTO(Long customerId){
		ArrayList<BookingSummary> pastBookings = new ArrayList<BookingSummary>();
		for(Booking booking : findByCustomerId(customerId)){
			if ((booking.getBookingSlot().getBookSlotDate().compareTo(LocalDate.now()) < 0 ||
			    (booking.getStatus() == Status.CANCELLED))) {
				pastBookings.add(new BookingSummary(booking));
			}
		}
		Collections.sort(pastBookings, new DateTimeSort());
		return pastBookings;
	}

	public Iterable<BookingSummary> getCurrentBookingsByCustomerIdDTO(Long customerId){
		ArrayList<BookingSummary> pastBookings = new ArrayList<BookingSummary>();
		for(Booking booking : findByCustomerId(customerId)){
			if((booking.getBookingSlot().getBookSlotDate().compareTo(LocalDate.now()) >= 0 &&
			   (booking.getStatus() != Status.CANCELLED))) {
				pastBookings.add(new BookingSummary(booking));
			}
		}
		Collections.sort(pastBookings, new DateTimeSort());
		return pastBookings;
	}

	public Iterable<BookingSummary> getPastBookingsByBusinessIdDTO(Long businessId){
		ArrayList<BookingSummary> pastBookings = new ArrayList<BookingSummary>();
		for(Booking booking : findByBusinessId(businessId)){
			if (booking.getBookingSlot().getBookSlotDate().compareTo(LocalDate.now()) < 0) {
				pastBookings.add(new BookingSummary(booking));
			}
		}
		Collections.sort(pastBookings, new DateTimeSort());
		return pastBookings;
	}

	public Iterable<Booking> getBookingsByWorker(String workerUsername){
		updateBookingStatus();
		return repository.findByWorkerUsername(workerUsername);
	}
	
	public Iterable<Booking> getAvailableBookingsByBusiness(Business business)
	{
		updateBookingStatus();
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
		updateBookingStatus();
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
		updateBookingStatus();
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
	
	public Iterable<BookingSummary> getNewestBookings(int noBookings){
		updateBookingStatus();
		ArrayList<Booking> bookingList = (ArrayList<Booking>) repository.findAll();
		Collections.sort(bookingList, Collections.reverseOrder());
		if(noBookings > bookingList.size())
		{
			noBookings = bookingList.size();
		}
		ArrayList<BookingSummary> newList = new ArrayList<BookingSummary>();
		for(int i = 0; i<noBookings;i++)
		{
			newList.add(new BookingSummary(bookingList.get(i)));
		}
		return newList;
	}
	
	public void updateBookingStatus()
	{
		for(Booking booking: repository.findAll())
		{
			if(LocalDateTime.of(booking.getBookingSlot().getDate(), booking.getBookingSlot().getEndTime()).isBefore(LocalDateTime.now())
					&& booking.getStatus() == Status.BOOKED)
			{
				booking.setStatusCompleted();
				repository.save(booking);
			}
		}
	}
}
