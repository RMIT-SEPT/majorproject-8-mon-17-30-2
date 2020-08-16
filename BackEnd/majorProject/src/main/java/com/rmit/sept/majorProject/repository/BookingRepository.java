package com.rmit.sept.majorProject.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Worker;

@Repository("bookingRepo")
public class BookingRepository {
	
	private List<Booking> bookingList;
	
	BookingRepository()
	{
		this.bookingList = new LinkedList<Booking>();
	}
	
	public List<Booking> getAllBooking()
	{
		return this.bookingList;
	}
	
	public List<Booking> getAllBookings(Business business)
	{
		List<Booking> businessBookingList = new LinkedList<Booking>();
		for(Booking booking:this.bookingList)
		{
			if(booking.getBusiness() == business)
			{
				businessBookingList.add(booking);
			}
		}
		return businessBookingList;
	}
	
	public List<Booking> getAllBookings(Worker worker)
	{
		List<Booking> workerBookingList = new LinkedList<Booking>();
		for(Booking booking:this.bookingList)
		{
			if(booking.getWorker() == worker)
			{
				workerBookingList.add(booking);
			}
		}
		return workerBookingList;
	}
	
	public List<Booking> getAllBookings(Customer customer)
	{
		List<Booking> customerBookingList = new LinkedList<Booking>();
		for(Booking booking:this.bookingList)
		{
			if(booking.getCustomer() == customer)
			{
				customerBookingList.add(booking);
			}
		}
		return customerBookingList;
	}
	
	public Booking getBooking(Long id)
	{
		for(Booking booking:this.bookingList)
		{
			if(booking.getBookingId() == id)
			{
				return booking;
			}
		}
		return null;
	}
	
	public Boolean addBooking(Booking booking)
	{
		for(Booking bookings:this.bookingList)
		{
			if(bookings == booking)
			{
				return false;
			}
		}
		this.bookingList.add(booking);
		return true;
	}
	
	public Boolean deleteBooking(Long id)
	{
		for(Booking booking:this.bookingList)
		{
			if(booking.getBookingId() == id)
			{
				this.bookingList.remove(booking);
				return true;
			}
		}
		return false;
	}
	
	public Boolean updateBooking(Long id, Booking booking)
	{
		int i = 0;
		for(Booking bookings:this.bookingList)
		{
			if(bookings.getBookingId() == id)
			{
				this.bookingList.set(i, booking);
				return true;
			}
			i++;
		}
		return false;
	}
}
