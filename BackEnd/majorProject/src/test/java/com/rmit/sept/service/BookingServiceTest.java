package com.rmit.sept.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.model.BookingSlot;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Service;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.service.BookingService;

@ExtendWith(SpringExtension.class)
public class BookingServiceTest {

	@TestConfiguration
	static class BookingServiceImplTestConcextConfiguration{
		@Bean
		public BookingService bookingService() {
			return new BookingService();
		}
	}
	@Autowired
	private BookingService bookingService;
	
	
	
	//Test add booking
	@Test
	public void testAddBooking() {
		Customer customerTest = new Customer("cust","custUser","custPass","custStreet","cust@email.com","3215321");
		Worker workerTest = new Worker();
		Business businessTest = new Business();
		Service serviceTest = new Service();
		BookingSlot bookingSlotTest = new BookingSlot();
		Booking bookingTest = new Booking(customerTest, workerTest, businessTest, serviceTest, bookingSlotTest);
		when(bookingService.duplicateBooking(bookingTest)).thenReturn(false);
		Booking result = bookingService.createNewBooking(bookingTest);
		assertEquals(bookingTest, result);
	}
	
	//Test get all bookings
	@Test
	public void testGetAllBookings() {
		
	}
	
	//Test delete a booking
	@Test
	public void testDeleteBooking() {
		
	}
	
}
