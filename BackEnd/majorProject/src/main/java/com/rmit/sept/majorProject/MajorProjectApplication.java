package com.rmit.sept.majorProject;

import com.rmit.sept.majorProject.model.*;
import com.rmit.sept.majorProject.repository.AdminRepository;
import com.rmit.sept.majorProject.repository.BookingRepository;
import com.rmit.sept.majorProject.repository.BookingSlotRepository;
import com.rmit.sept.majorProject.repository.BusinessRepository;
import com.rmit.sept.majorProject.repository.CustomerRepository;
import com.rmit.sept.majorProject.repository.ServiceRepository;
import com.rmit.sept.majorProject.repository.WorkSlotRepository;
import com.rmit.sept.majorProject.repository.WorkerRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MajorProjectApplication {

	/*
		Testing login api with database initialisation
	 */
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private WorkerRepository workerRepository;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private ServiceRepository serviceRepository;
	@Autowired
	private BookingSlotRepository bookingSlotRepository;
	@Autowired
	private WorkSlotRepository workSlotRepository;
	@Autowired
	private BusinessRepository businessRepository;

	public static void main(String[] args) {
		SpringApplication.run(MajorProjectApplication.class, args);
	}

	//TEST DATA
	@Bean
	InitializingBean sendDatabase() {
		return () -> {

			//---create people---

			//customers
			Customer austin = new Customer("Austin", "aus", "pass", "address", "customer@bookworm.com", "12345");

			//workers
			Worker john = new Worker("John", "john", "pword", "worker@bookworm.com", "address", "12345");
			Worker tom = new Worker("Tom", "tom", "blisters", "fish@restaurant.com", "in a restaurant", "647382");

			//admins/businessowners
			Admin caramel = new Admin("Admin", "caramel6", "password");			
			Admin puzzleboy = new Admin("Puzzle Boy","puzzles", "are fun");

			//create a business
			Business barber = new Business("Barber");
			barber.addWorker(john);
			barber.setBusinessOwner(caramel);			
			Business restaurant = new Business("Restaurant");
			restaurant.addWorker(tom);
			restaurant.setBusinessOwner(puzzleboy);
			
			//create date/times
			LocalDate day              = LocalDate.of(2021, 12, 31);
			LocalDate oldDay           = LocalDate.of(2007, 9, 25);
			LocalTime shiftStartTime   = LocalTime.of(10, 00);
			LocalTime shiftEndTime     = LocalTime.of(17, 00);
			LocalTime bookingStartTime = LocalTime.of(14, 30);
			LocalTime bookingEndTime   = LocalTime.of(15, 00);

			//services
			Service haircut = new Service("Haircut", "Cut off absolutely all of your hair", 1);
			Service beardtrim = new Service("Beard Trim", "Get your beard trimmed or shaved", 1);		
			Service reserveTable = new Service("Book Table", "Book a table at the restaurant", 1);

			//john offers haircuts or beard trims during his working slots
			List<Service> johnServices = new ArrayList<Service>();
			johnServices.add(haircut);
			johnServices.add(beardtrim);
			john.setServices(johnServices);
			
			List<Service> tomServices = new ArrayList<Service>();
			tomServices.add(reserveTable);
			tomServices.add(haircut);		//Never had a haircut at a restaurant? Me neither.	
			tom.setServices(tomServices);
			

			//upcoming workslot and bookingslot for schedule and available booking testing
			WorkSlot johnShift = new WorkSlot(day, shiftStartTime, shiftEndTime, john);
			BookingSlot johnSlot = new BookingSlot(day, bookingStartTime, bookingEndTime, johnServices);
			johnShift.addBookingSlot(johnSlot);
			
			//Tom likes to work a lot 
//			WorkSlot tomShift0 = new WorkSlot()
			
			// // //old shift and bookingslot for past booking/booking history testing
			WorkSlot oldShift = new WorkSlot(oldDay, shiftStartTime, shiftEndTime, john);
			BookingSlot oldSlot = new BookingSlot(oldDay, bookingStartTime, bookingEndTime, johnServices);
			oldShift.addBookingSlot(oldSlot);

			// //upcoming bookings
			Booking austinBooking = new Booking(austin, john, barber, haircut, johnSlot);		

			// //old bookings
			Booking oldBooking = new Booking(austin, john, barber, beardtrim, oldSlot);
			Booking oldBooking1 = new Booking(austin, john, barber, beardtrim, oldSlot);
			Booking oldBooking2 = new Booking(austin, john, barber, beardtrim, oldSlot);

			//save everything to repos (do this at the end to avoid detach errors)
			businessRepository.save(barber);
			businessRepository.save(restaurant);
			adminRepository.save(caramel);
			workerRepository.save(john);
			serviceRepository.save(haircut);
			serviceRepository.save(beardtrim);
			customerRepository.save(austin);
			workSlotRepository.save(johnShift);
			workSlotRepository.save(oldShift);
			bookingSlotRepository.save(johnSlot);
			bookingSlotRepository.save(oldSlot);			
			bookingRepository.save(austinBooking);
			bookingRepository.save(oldBooking);
			bookingRepository.save(oldBooking1);
			bookingRepository.save(oldBooking2);
			
		};
	}

}
