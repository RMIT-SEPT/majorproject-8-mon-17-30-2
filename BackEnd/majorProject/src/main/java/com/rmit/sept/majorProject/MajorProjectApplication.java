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

			//admins/businessowners
			Admin caramel = new Admin("Admin", "caramel6", "password");			

			//create a business
			Business barber = new Business("Barber");
			barber.addWorker(john);
			barber.setBusinessOwner(caramel);			

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

			//john offers haircuts or beard trims during his working slots
			List<Service> johnServices = new ArrayList<Service>();
			johnServices.add(haircut);
			johnServices.add(beardtrim);
			john.setServices(johnServices);
			barber.addService(haircut);
			barber.addService(beardtrim);

			//upcoming workslot and bookingslot for schedule and available booking testing
			WorkSlot johnShift = new WorkSlot(day, shiftStartTime, shiftEndTime, john);
			BookingSlot johnSlot = new BookingSlot(day, bookingStartTime, bookingEndTime, johnServices);
			johnShift.addBookingSlot(johnSlot);

			// // //old shift and bookingslot for past booking/booking history testing
			WorkSlot oldShift = new WorkSlot(oldDay, shiftStartTime, shiftEndTime, john);
			BookingSlot oldSlot = new BookingSlot(oldDay, bookingStartTime, bookingEndTime, johnServices);
			oldShift.addBookingSlot(oldSlot);

			// //upcoming bookings
			Booking austinBooking = new Booking(austin, john, barber, haircut, johnSlot);
			
			// vacant upcoming bookingSlots
			LocalDate newDay1 = day.plusDays(1);
			LocalDate newDay2 = day.plusDays(2);
			WorkSlot newShift1 = new WorkSlot(newDay1, shiftStartTime, shiftEndTime, john);
			WorkSlot newShift2 = new WorkSlot(newDay2, shiftStartTime, shiftEndTime, john);
			List<Service> newService = new ArrayList<Service>();
			List<Service> newService2 = new ArrayList<Service>();
			newService.add(beardtrim);
			newService2.add(haircut);
			BookingSlot newSlot1 = new BookingSlot(newDay1, bookingStartTime, bookingEndTime, newService);
			BookingSlot newSlot2 = new BookingSlot(newDay2, bookingStartTime, bookingEndTime, newService2);
			newShift1.addBookingSlot(newSlot1);
			newShift2.addBookingSlot(newSlot2);

			// //old bookings
			Booking oldBooking = new Booking(austin, john, barber, beardtrim, oldSlot);
			Booking oldBooking1 = new Booking(austin, john, barber, beardtrim, oldSlot);
			Booking oldBooking2 = new Booking(austin, john, barber, beardtrim, oldSlot);

			//save everything to repos (do this at the end to avoid detach errors)
			businessRepository.save(barber);
			adminRepository.save(caramel);
			workerRepository.save(john);
			serviceRepository.save(haircut);
			serviceRepository.save(beardtrim);
			customerRepository.save(austin);
			workSlotRepository.save(johnShift);
			workSlotRepository.save(oldShift);
			workSlotRepository.save(newShift1);
			workSlotRepository.save(newShift2);
			bookingSlotRepository.save(johnSlot);
			bookingSlotRepository.save(oldSlot);
			bookingSlotRepository.save(newSlot1);
			bookingSlotRepository.save(newSlot2);			
			bookingRepository.save(austinBooking);
			bookingRepository.save(oldBooking);
			bookingRepository.save(oldBooking1);
			bookingRepository.save(oldBooking2);
			
		};
	}

}
