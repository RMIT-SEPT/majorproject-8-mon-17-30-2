package com.rmit.sept.majorProject;

import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.model.Booking;
import com.rmit.sept.majorProject.model.BookingSlot;
import com.rmit.sept.majorProject.model.Customer;
import com.rmit.sept.majorProject.model.Service;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.model.WorkSlot;
import com.rmit.sept.majorProject.repository.AdminRepository;
import com.rmit.sept.majorProject.repository.BookingRepository;
import com.rmit.sept.majorProject.repository.BookingSlotRepository;
import com.rmit.sept.majorProject.repository.CustomerRepository;
import com.rmit.sept.majorProject.repository.ServiceRepository;
import com.rmit.sept.majorProject.repository.WorkSlotRepository;
import com.rmit.sept.majorProject.repository.WorkerRepository;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.EntityManager;

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

	public static void main(String[] args) {
		SpringApplication.run(MajorProjectApplication.class, args);
	}

	//TEST DATA
	@Bean
	InitializingBean sendDatabase() {
		return () -> {

			//create people
			Customer austin = new Customer("Austin", "aus", "pass", "address", "customer@bookworm.com", "12345");
			customerRepository.save(austin);
			Worker john = new Worker("John", "john", "pword", "worker@bookworm.com", "address", "12345");
			workerRepository.save(john);
			Admin caramel = new Admin("Admin", "caramel6", "password");
			adminRepository.save(caramel);

			//create date/times
			LocalDate day              = LocalDate.of(2021, 8, 30);
			LocalTime shiftStartTime   = LocalTime.of(10, 00);
			LocalTime shiftEndTime     = LocalTime.of(17, 00);
			LocalTime bookingStartTime = LocalTime.of(14, 30);
			LocalTime bookingEndTime   = LocalTime.of(15, 00);

			//services
			Service haircut = new Service("Haircut", "Cut off absolutely all of your hair", 1);
			serviceRepository.save(haircut);

			//create workslot/bookingslot
			WorkSlot johnShift = new WorkSlot(day, shiftStartTime, shiftEndTime, workerRepository.findByUsername("john"));
			BookingSlot johnSlot = new BookingSlot(day, bookingStartTime, bookingEndTime, serviceRepository.findByTitle("Haircut"));
			johnShift.addBookingSlot(johnSlot);
			workSlotRepository.save(johnShift);
			bookingSlotRepository.save(johnSlot);

			//bookings
//			Booking austinBooking = new Booking(customerRepository.findByUsername("aus"), workerRepository.findByUsername("john"), null, serviceRepository.findByTitle("Haircut"), bookingSlotRepository.getNewest());
//			bookingRepository.save(austinBooking);

		};
	}

}
