package com.rmit.sept.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rmit.sept.majorProject.dto.BookingBlueprint;
import com.rmit.sept.majorProject.dto.BookingSummary;
import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.model.BookingSlot;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Service;
import com.rmit.sept.majorProject.model.WorkSlot;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.repository.BookingRepository;
import com.rmit.sept.majorProject.repository.BookingSlotRepository;
import com.rmit.sept.majorProject.repository.BusinessRepository;
import com.rmit.sept.majorProject.repository.ServiceRepository;
import com.rmit.sept.majorProject.service.BookingService;
import com.rmit.sept.majorProject.service.BookingSlotService;
import com.rmit.sept.majorProject.service.BusinessService;
import com.rmit.sept.majorProject.service.CustomerService;
import com.rmit.sept.majorProject.service.ServiceService;
import com.rmit.sept.majorProject.service.WorkerService;
@ExtendWith(SpringExtension.class)
public class BookingServiceTest {

	@TestConfiguration
	static class BookingServiceImplTestContextConfiguration{
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
	private BusinessService businessService;
	@MockBean
	private CustomerService custSevice;
	@MockBean
	private ServiceService serviceService;
	@MockBean
	private BookingSlotService bookingSlotService;
	@MockBean
	private ServiceRepository servRepository;
	@MockBean
	private BusinessRepository busiRepository;
	@MockBean
	private BookingSlotRepository bookingSlotRepository;
	
	Customer customerTest;
	Worker workerTest;
	Business businessTest;
	Service serviceTest;
	ArrayList<Service> serviceList;
	WorkSlot workSlotTest;
	BookingSlot bookingSlotTest;
	BookingSlot bookingSlotLateTest;
	Booking bookingTest;
	BookingBlueprint bookingTestBlueprint;
	
	@BeforeEach
	public void init() {
		customerTest = new Customer("cust","custUser","custPass","custStreet","cust@email.com","3215321");
		workerTest = new Worker("worker","workerUser","workerPass","worker@email.com","0 Worker Street", "123456789");
		businessTest = new Business("Busi Business");
		serviceTest = new Service("Deliver Packages", "We deliver those packages", 3);
		serviceList = new ArrayList<Service>();
		workSlotTest = new WorkSlot(LocalDate.of(2020, 12, 20), LocalTime.of(5,30), LocalTime.of(12,30), workerTest);
		bookingSlotTest = new BookingSlot(LocalDate.of(2020, 12, 20), LocalTime.of(5,30), LocalTime.of(12,30), serviceList);
		serviceList.add(serviceTest);
		customerTest.setId(1L);
		workerTest.setId(1L);
		workerTest.setBusiness(businessTest);
		bookingSlotTest.setId(1L);
		businessTest.setId(1L);
		serviceTest.setId(1L);
		bookingTestBlueprint = new BookingBlueprint(customerTest.getId(), workerTest.getId(), businessTest.getId(), bookingSlotTest.getId(), serviceTest.getId());
		bookingTest = new Booking(customerTest, workerTest, businessTest, serviceTest, bookingSlotTest);
		bookingTest.setBookingId(1L);
		workSlotTest.addBookingSlot(bookingSlotTest);
	}

	//Test add booking - with correct details
	@Test
	public void testAddBooking_WithCorrectDetails() {
		when(this.workerService.findById(bookingTestBlueprint.getWorkerId())).thenReturn(Optional.of(workerTest));
		when(this.custSevice.findById(bookingTestBlueprint.getCustomerId())).thenReturn(Optional.of(customerTest));
		when(this.serviceService.findById(bookingTestBlueprint.getServiceId())).thenReturn(serviceTest);
		when(this.businessService.findById(bookingTestBlueprint.getBusinessId())).thenReturn(businessTest);
		when(this.bookingSlotService.findById(bookingTestBlueprint.getBookingSlotId())).thenReturn(bookingSlotTest);
		
		when(this.bookingSlotRepository.save(bookingSlotTest)).thenReturn(bookingSlotTest);
		
		when(this.bookingRepository.save(bookingTest)).thenReturn(bookingTest);
		
		BookingSummary result = bookingService.createNewBooking(bookingTestBlueprint);
		assertEquals(new BookingSummary(bookingTest), result);
	}
	
	//Test add bookings- with non existent Customer
	@Test
	public void testAddBooking_WithFakeCustomer() {
		when(this.workerService.findById(bookingTestBlueprint.getWorkerId())).thenReturn(Optional.of(workerTest));
		when(this.custSevice.findById(bookingTestBlueprint.getCustomerId())).thenReturn(Optional.ofNullable(null));
		when(this.serviceService.findById(bookingTestBlueprint.getServiceId())).thenReturn(serviceTest);
		when(this.businessService.findById(bookingTestBlueprint.getBusinessId())).thenReturn(businessTest);
		when(this.bookingSlotService.findById(bookingTestBlueprint.getBookingSlotId())).thenReturn(bookingSlotTest);
		when(this.bookingRepository.save(bookingTest)).thenReturn(bookingTest);
		//Asserts if createNewBoooking throws the NoSuchElementException
		Assertions.assertThrows(NoSuchElementException.class, () -> {
			bookingService.createNewBooking(bookingTestBlueprint);
		  });
	}
	
	//Test add bookings- with non existent Worker
	@Test
	public void testAddBooking_WithFakeWorker() {
		when(this.workerService.findById(bookingTestBlueprint.getWorkerId())).thenReturn(Optional.ofNullable(null));
		when(this.custSevice.findById(bookingTestBlueprint.getCustomerId())).thenReturn(Optional.of(customerTest));
		when(this.serviceService.findById(bookingTestBlueprint.getServiceId())).thenReturn(serviceTest);
		when(this.businessService.findById(bookingTestBlueprint.getBusinessId())).thenReturn(businessTest);
		when(this.bookingSlotService.findById(bookingTestBlueprint.getBookingSlotId())).thenReturn(bookingSlotTest);
		when(this.bookingRepository.save(bookingTest)).thenReturn(bookingTest);
		//Asserts if createNewBoooking throws the NoSuchElementException
		Assertions.assertThrows(NoSuchElementException.class, () -> {
			bookingService.createNewBooking(bookingTestBlueprint);
		  });
	}
	
	//Test add bookings- with non existent Business
	@Test
	public void testAddBooking_WithFakeBusiness() {
		when(this.workerService.findById(bookingTestBlueprint.getWorkerId())).thenReturn(Optional.of(workerTest));
		when(this.custSevice.findById(bookingTestBlueprint.getCustomerId())).thenReturn(Optional.of(customerTest));
		when(this.serviceService.findById(bookingTestBlueprint.getServiceId())).thenReturn(serviceTest);
		when(this.businessService.findById(bookingTestBlueprint.getBusinessId())).thenReturn(null);
		when(this.bookingSlotService.findById(bookingTestBlueprint.getBookingSlotId())).thenReturn(bookingSlotTest);
		when(this.bookingRepository.save(bookingTest)).thenReturn(bookingTest);
		//Asserts if createNewBoooking throws the DataRetrievalFailureException
		Assertions.assertThrows(DataRetrievalFailureException.class, () -> {
			bookingService.createNewBooking(bookingTestBlueprint);
		  });
	}
	
	//Test add bookings- with non existent Booking slot
	@Test
	public void testAddBooking_WithFakeBookingSlot() {
		when(this.workerService.findById(bookingTestBlueprint.getWorkerId())).thenReturn(Optional.of(workerTest));
		when(this.custSevice.findById(bookingTestBlueprint.getCustomerId())).thenReturn(Optional.of(customerTest));
		when(this.serviceService.findById(bookingTestBlueprint.getServiceId())).thenReturn(serviceTest);
		when(this.businessService.findById(bookingTestBlueprint.getBusinessId())).thenReturn(businessTest);
		when(this.bookingSlotService.findById(bookingTestBlueprint.getBookingSlotId())).thenReturn(null);
		when(this.bookingRepository.save(bookingTest)).thenReturn(bookingTest);
		//Asserts if createNewBoooking throws the DataRetrievalFailureException
		Assertions.assertThrows(DataRetrievalFailureException.class, () -> {
			bookingService.createNewBooking(bookingTestBlueprint);
		  });
	}
	
	//Test add bookings- with non existent Service
	@Test
	public void testAddBooking_WithFakeService() {
		when(this.workerService.findById(bookingTestBlueprint.getWorkerId())).thenReturn(Optional.of(workerTest));
		when(this.custSevice.findById(bookingTestBlueprint.getCustomerId())).thenReturn(Optional.of(customerTest));
		when(this.serviceService.findById(bookingTestBlueprint.getServiceId())).thenReturn(null);
		when(this.businessService.findById(bookingTestBlueprint.getBusinessId())).thenReturn(businessTest);
		when(this.bookingSlotService.findById(bookingTestBlueprint.getBookingSlotId())).thenReturn(bookingSlotTest);
		when(this.bookingRepository.save(bookingTest)).thenReturn(bookingTest);
		//Asserts if createNewBoooking throws the DataRetrievalFailureException
		Assertions.assertThrows(DataRetrievalFailureException.class, () -> {
			bookingService.createNewBooking(bookingTestBlueprint);
		  });
	}
	
	//Test add bookings- with a duplicate booking
	@Test
	public void testAddBooking_WithDuplicateBooking() {
		BookingService bookingSpy = spy(bookingService);
		when(this.workerService.findById(bookingTestBlueprint.getWorkerId())).thenReturn(Optional.of(workerTest));
		when(this.custSevice.findById(bookingTestBlueprint.getCustomerId())).thenReturn(Optional.of(customerTest));
		when(this.serviceService.findById(bookingTestBlueprint.getServiceId())).thenReturn(serviceTest);
		when(this.businessService.findById(bookingTestBlueprint.getBusinessId())).thenReturn(businessTest);
		when(this.bookingSlotService.findById(bookingTestBlueprint.getBookingSlotId())).thenReturn(bookingSlotTest);
		doReturn(true).when(bookingSpy).duplicateBooking(Mockito.isNotNull());
		when(this.bookingRepository.save(bookingTest)).thenReturn(bookingTest);
		//Asserts if createNewBoooking throws the DuplicateKeyException
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
	
	
	//Cancel booking with existing booking
	@Test
	public void testCancelBooking_withExistingBooking() {
		when(bookingRepository.findById(1L)).thenReturn(Optional.of(bookingTest));
		assertEquals(bookingService.cancelBooking(1L), true);		
	}
	
	//Cancel booking that is scheduled within less than 48 hours
	@Test
	public void testCancelBooking_within48hours() {
		Booking lateBooking = bookingTest; 
		lateBooking.getBookingSlot().setDate(LocalDate.now().plusDays(1L));
		when(bookingRepository.findById(1L)).thenReturn(Optional.of(lateBooking));
		Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			bookingService.cancelBooking(1L);
		  });
	}
	
	//Cancel booking with an invalid booking id
	@Test
	public void testCancelBooking_invalidBookingId() {
		when(bookingRepository.findById(1L)).thenReturn(Optional.empty());
		assertEquals(bookingService.cancelBooking(1L), false);		
	}
	
	
	
}
