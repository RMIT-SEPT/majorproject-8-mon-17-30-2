package com.rmit.sept.majorProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Person;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.repository.BookingRepository;
import com.rmit.sept.majorProject.repository.PersonRepository;

@Service
public class BookingService {

	private final BookingRepository bookingRepository;
	
	BookingService(@Qualifier("bookingRepo") BookingRepository bookingRepository)
	{
		this.bookingRepository = bookingRepository;
	}
	
	public List<Booking> getAllBookings()
	{
		return null;
	}
	
	public List<Booking> getAllBookings(Business business)
	{
		return null;
	}
	
	public List<Booking> getAllBookings(Worker worker)
	{
		return null;
	}
	public List<Booking> getAllBookings(Customer customer)
	{
		return null;
	}
	public Person getBooking(Long ID)
	{
		return null;
	}
	public Boolean addBooking(Booking booking)
	{
		return false;
	}
	public Boolean deleteBooking(Long ID)
	{
		return false;
	}
	public Boolean updateBooking(Long ID, Booking booking)
	{
		return false;
	}
}
