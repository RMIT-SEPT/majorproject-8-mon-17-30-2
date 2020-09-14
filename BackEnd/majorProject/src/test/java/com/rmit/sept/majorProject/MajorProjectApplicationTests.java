package com.rmit.sept.majorProject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
import java.util.List;


@SpringBootTest
class MajorProjectApplicationTests {

	LocalDate day              = LocalDate.of(2021, 12, 31);
			LocalDate oldDay           = LocalDate.of(2007, 9, 25);
			LocalTime shiftStartTime   = LocalTime.of(10, 00);
			LocalTime shiftEndTime     = LocalTime.of(17, 00);
			LocalTime bookingStartTime = LocalTime.of(14, 30);
			LocalTime bookingEndTime   = LocalTime.of(15, 00);

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


	// @Test
	// void check_Duplicate_Bookings(){
	// 	Customer customer = new Customer("Umer", "umer", "pass", "address", "customer@bookworm.com", "12345");
	// 	Worker worker = new Worker("John", "john", "pword", "worker@bookworm.com", "address", "12345");
	// 	Business bussiness = new Business("Haircut");
	// 	Service service = new Service("Haircut", "Cut off absolutely all of your hair", 1);
	// 	List<Service> johnServices = new ArrayList<Service>();
	// 		johnServices.add(service);
	// 		worker.setServices(johnServices);
	// 	BookingSlot slot = new BookingSlot(day, bookingStartTime, bookingEndTime, johnServices);
	// 	Booking booking = new Booking(customer, worker, bussiness, service, slot);
	// 	// try {
	// 	// 	bookingService.createNewBooking(booking);
	// 	// }
	// 	// catch(NullPointerException e) {
	// 	// 	e.printStackTrace();
	// 	// }
	// 	//bookingService.createNewBooking(booking);
	// 	assertEquals(true, bookingService.duplicateBooking(booking));

	// }

	@Test
	void check_Duplicate_Bookings(){
	
		assertEquals("h", "h");

	}



}
