package com.rmit.sept.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rmit.sept.majorProject.dto.BookingBlueprint;
import com.rmit.sept.majorProject.dto.BookingSummary;
import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.model.BookingSlot;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Service;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.repository.BookingRepository;
import com.rmit.sept.majorProject.repository.BookingSlotRepository;
import com.rmit.sept.majorProject.repository.BusinessRepository;
import com.rmit.sept.majorProject.repository.ServiceRepository;
import com.rmit.sept.majorProject.service.BookingService;
import com.rmit.sept.majorProject.service.CustomerService;
import com.rmit.sept.majorProject.service.WorkerService;

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
	@MockBean
	private BookingRepository bookingRepository;
	@MockBean
	private WorkerService workerService;
	@MockBean
	private CustomerService custSevice;
	@MockBean
	private ServiceRepository servRepository;
	@MockBean
	private BusinessRepository busiRepository;
	@MockBean
	private BookingSlotRepository bookingSlotRepository;
	
	Customer customerTest = new Customer("cust","custUser","custPass","custStreet","cust@email.com","3215321");
	Worker workerTest = new Worker("worker","workerUser","workerPass","worker@email.com","0 Worker Street", "123456789");
	Business businessTest = new Business("Busi Business");
	static Service serviceTest = new Service("Deliver Packages", "We deliver those packages", 3);
	static ArrayList<Service> serviceList = new ArrayList<Service>();
	BookingSlot bookingSlotTest = new BookingSlot(LocalDate.of(2020, 12, 20), LocalTime.of(5,30), LocalTime.of(12,30), serviceList);
	Booking bookingTest = new Booking(customerTest, workerTest, businessTest, serviceTest, bookingSlotTest);
	BookingBlueprint bookingTestBlueprint = new BookingBlueprint(customerTest.getId(), workerTest.getId(), businessTest.getId(), serviceTest.getId(), bookingSlotTest.getId());
	
	@BeforeAll
	public static void init() {
		serviceList.add(serviceTest);
	}
	
	//Test add booking - with correct details
	@Test
	public void testAddBooking_WithCorrectDetails() {
		when(this.workerService.findByUsername(bookingTest.getWorker().getUsername())).thenReturn(bookingTest.getWorker());
		when(this.custSevice.findByUsername(bookingTest.getCustomer().getUsername())).thenReturn(bookingTest.getCustomer());
		when(this.servRepository.findByTitle(bookingTest.getService().getTitle())).thenReturn(bookingTest.getService());
		when(this.busiRepository.findByBusinessName(bookingTest.getBusiness().getBusinessName())).thenReturn(bookingTest.getBusiness());
		when(this.bookingSlotRepository.findById(bookingTest.getBookingSlot().getId())).thenReturn(Optional.of(bookingTest.getBookingSlot()));
		when(this.bookingRepository.save(bookingTest)).thenReturn(bookingTest);
		
		BookingSummary result = bookingService.createNewBooking(bookingTestBlueprint);
		assertEquals(new BookingSummary(bookingTest), result);
	}
	
	//Test add bookings- with non existent Service
	@Test
	public void testAddBooking_WithFakeService() {
		when(this.workerService.findByUsername(bookingTest.getWorker().getUsername())).thenReturn(bookingTest.getWorker());
		when(this.custSevice.findByUsername(bookingTest.getCustomer().getUsername())).thenReturn(bookingTest.getCustomer());
		when(this.servRepository.findByTitle(bookingTest.getService().getTitle())).thenReturn(null);
		when(this.busiRepository.findByBusinessName(bookingTest.getBusiness().getBusinessName())).thenReturn(bookingTest.getBusiness());
		when(this.bookingSlotRepository.findById(bookingTest.getBookingSlot().getId())).thenReturn(Optional.of(bookingTest.getBookingSlot()));
		when(this.bookingRepository.save(bookingTest)).thenReturn(bookingTest);
		Assertions.assertThrows(DataRetrievalFailureException.class, () -> {
			bookingService.createNewBooking(bookingTestBlueprint);
		  });
	}
	
	//Test add bookings- with a duplicate booking
	@Test
	public void testAddBooking_WithDuplicateBooking() {
		BookingService bookingSpy = spy(bookingService);
		when(this.workerService.findByUsername(bookingTest.getWorker().getUsername())).thenReturn(bookingTest.getWorker());
		when(this.custSevice.findByUsername(bookingTest.getCustomer().getUsername())).thenReturn(bookingTest.getCustomer());
		when(this.servRepository.findByTitle(bookingTest.getService().getTitle())).thenReturn(bookingTest.getService());
		when(this.busiRepository.findByBusinessName(bookingTest.getBusiness().getBusinessName())).thenReturn(bookingTest.getBusiness());
		when(this.bookingSlotRepository.findById(bookingTest.getBookingSlot().getId())).thenReturn(Optional.of(bookingTest.getBookingSlot()));
		doReturn(true).when(bookingSpy).duplicateBooking(Mockito.isNotNull());
		when(this.bookingRepository.save(bookingTest)).thenReturn(bookingTest);
		Assertions.assertThrows(DuplicateKeyException.class, () -> {
			bookingSpy.createNewBooking(bookingTestBlueprint);
		  });
	}
	
	//Test get all bookings with some bookings in the system
	@Test
	public void testGetAllBookings_WithSomeBookings() {
		ArrayList<Booking> bookingList = new ArrayList<Booking>();
		bookingList.add(bookingTest);
		
		when(this.bookingRepository.findAll()).thenReturn(bookingList);
		
		ArrayList<Booking> result = (ArrayList<Booking>) bookingService.getAllBookings();
		assertEquals(bookingList, result);
	}
	
	//Test get all bookings with no bookings in the system
	@Test
	public void testGetAllBookings_WithNoBookings() {
		ArrayList<Booking> bookingList = new ArrayList<Booking>();
		when(this.bookingRepository.findAll()).thenReturn(bookingList);

		ArrayList<Booking> result = (ArrayList<Booking>) bookingService.getAllBookings();
		assertEquals(bookingList, result);
	}
	
	//Test delete a booking
	@Test
	public void testDeleteBooking() {
		
	}
	
}
