package com.rmit.sept.majorProject.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

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
			if(booking.getBusiness == business)
			{
				businessBookingList.add(booking);
			}
		}
		return businessBookingList;
	}
	
	public List<Worker> getAllBookings(Worker worker)
	{
		List<Worker> workerBookingList = new LinkedList<Worker>();
		for(Booking booking:this.bookingList)
		{
			if(booking.getWorker == worker)
			{
				workerBookingList.add(booking);
			}
		}
		return workerBookingList;
	}
	
	public List<Customer> getAllBookings(Customer customer)
	{
		List<Customer> customerBookingList = new LinkedList<Customer>();
		for(Booking booking:this.bookingList)
		{
			if(booking.getCustomer == customer)
			{
				customerBookingList.add(booking);
			}
		}
		return customerBookingList;
	}
	
	Booking getBooking(Long ID)
	{
		for(Booking booking:this.bookingList)
		{
			if(booking.getBookingID== ID)
			{
				return booking;
			}
		}
		return null;
	}
	
	Boolean addBooking(Booking booking)
	{
		for(Booking booking:this.bookingList)
		{
			if(booking.getCustomer == booking)
			{
				return false;
			}
		}
		this.bookingList.add(booking);
		return true;
	}
	
	Boolean deleteBooking(Long ID)
	{
		for(Booking booking:this.bookingList)
		{
			if(booking.getBookingID == ID)
			{
				this.bookingList.remove(booking);
				return true;
			}
		}
		return false;
	}
	
	Boolean updateBooking(Long ID, Booking booking)
	{
		return false;
	}
}
