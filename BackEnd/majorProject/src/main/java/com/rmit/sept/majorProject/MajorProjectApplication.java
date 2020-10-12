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
			Customer stachenscarfen = new Customer("Stachenscarfen", "stachen", "hehehe", "22 aldus st", "mustache@scarf.com", "7684231");
			Customer luke = new Customer("Luke", "luke", "triton", "51 prof av", "blueboy@puzzle.com", "517861351");
			Customer hershel = new Customer("Hershel", "hershel", "Layton", "74 Stanbury ln", "tophat@gressenheller.com", "626176876");
			Customer flora = new Customer("Flora", "flora", "ladyviolet", "15 Mystere st", "flora@reinhold.com", "421617642");

			//workers
			Worker john = new Worker("John", "john", "pword", "worker@bookworm.com", "address", "12345");
			Worker tom = new Worker("Tom", "tom", "blisters", "fish@restaurant.com", "46 restaurant ln", "647382");
			Worker scott = new Worker("Scott", "scott", "thewoz", "scottthewoz@madden08.com", "34 room st", "81813108");
			Worker seth = new Worker("Seth", "seth", "etsca", "professionalPostman@auspost.com.au", "3 mailbox st", "97770007");
			Worker mbeke = new Worker("Mbeke", "Mmbeke", "snakes", "superstar@auspost.com.au", "64 village st", "52781853");
			Worker jcd = new Worker("JC", "jcd", "bionicman", "whatashame@unacto.com", "11 Antarctica St", "820989868");
			Worker paul = new Worker("Paul", "pdenton", "chameleon", "pauld@unacto.com", "14 Ton Hotel", "33344346");
			Worker walter = new Worker("Walter", "walter", "hoyhoy", "fishermansson@fishy.com", "24 Underground av", "43782115");
			Worker isabeau = new Worker("Isabeau", "isabeau", "moirai", "ladyhawke@yahoo.com", "20 Rooftop cl", "43627683");
			
			//admins/businessowners
			Admin caramel = new Admin("Admin", "caramel6", "password");			
			Admin puzzleboy = new Admin("Puzzle Boy","puzzles", "are fun");
			Admin mandy = new Admin("Mandy", "manG", "ahhhh");
			Admin bob = new Admin("Bob", "Page", "Alles");
			Admin flynn= new Admin("Flynn", "flynn", "neutral");
			
			//create a business
			Business barber = new Business("Caramel Barber");
			barber.addWorker(john);
			barber.setBusinessOwner(caramel);			
			
			Business restaurant = new Business("Puzzleboy Restaurant");
			restaurant.addWorker(tom);
			restaurant.addWorker(scott);
			restaurant.setBusinessOwner(puzzleboy);
			
			Business delivery = new Business("Mandy's Delivery Service");
			delivery.addWorker(seth);
			delivery.addWorker(mbeke);
			delivery.setBusinessOwner(mandy);
			
			Business consultancy = new Business("Page Consultancy");
			consultancy.addWorker(jcd);
			consultancy.addWorker(paul);
			consultancy.setBusinessOwner(bob);
			
			Business exterminator = new Business("Flynn's Exterminator Service");
			exterminator.addWorker(walter);
			exterminator.addWorker(isabeau);
			exterminator.setBusinessOwner(flynn);
			
			//create date/times
			LocalDate day              = LocalDate.of(2021, 12, 31);
			LocalDate oldDay           = LocalDate.of(2007, 9, 25);
			LocalDate oldDay2          = LocalDate.of(2009, 10, 30);
			LocalDate oldDay3          = LocalDate.of(2010, 1, 30);
			LocalTime shiftStartTime   = LocalTime.of(10, 00);
			LocalTime shiftEndTime     = LocalTime.of(17, 00);
			LocalTime bookingStartTime = LocalTime.of(14, 30);
			LocalTime bookingEndTime   = LocalTime.of(15, 00);

			//services
			Service haircut = new Service("Haircut", "Cut off absolutely all of your hair", 1);
			Service beardtrim = new Service("Beard Trim", "Get your beard trimmed or shaved", 1);
			barber.addService(haircut);
			barber.addService(beardtrim);		
			
			Service reserveTable = new Service("Reserve Table", "Book a table and attend to them", 1);
			Service birthday = new Service("Birthday Service", "Book a room for a birthday celebration and attend to them", 2);
			Service foodShow = new Service("Cooking presentation", "Cook your food in front of you and put on a presentation", 1);
			restaurant.addService(reserveTable);
			restaurant.addService(birthday);
			restaurant.addService(foodShow);
			
			Service movingService = new Service("Moving", "Come to you and move your stuff somewhere else", 2);
			Service smuggling = new Service("Smuggle", "We can smuggle anything out of the country", 1);
			delivery.addService(movingService);
			delivery.addService(smuggling);
			
			Service strategy = new Service("Strategy Consultation", "Consult financial strategy",1);
			Service organisation = new Service("Organisation Consultation", "Consult organisational strategy",1);
			Service training = new Service("Training Service", "Train people for your company", 2);
			consultancy.addService(strategy);
			consultancy.addService(organisation);
			consultancy.addService(training);
			
			Service pestExt = new Service("Pest Extermination", "We kill those pests", 1);
			Service animal = new Service("Relocate Animals", "Relocate animals that wandered into neighbourhoods", 1);
			exterminator.addService(pestExt);
			exterminator.addService(animal);
					
			//john offers haircuts or beard trims during his working slots
			List<Service> johnServices = new ArrayList<Service>();
			johnServices.add(haircut);
			johnServices.add(beardtrim);
			john.setServices(johnServices);
			barber.addService(haircut);
			barber.addService(beardtrim);
			
			List<Service> tomServices = new ArrayList<Service>();
			tomServices.add(reserveTable);
			tomServices.add(birthday);
			tom.setServices(tomServices);
			
			List<Service> scottServices = new ArrayList<Service>();
			scottServices.add(reserveTable);
			scottServices.add(foodShow);
			scott.setServices(scottServices);
			
			List<Service> sethServices = new ArrayList<Service>();
			sethServices.add(movingService);
			sethServices.add(smuggling);	//Totally not something illegal
			seth.setServices(sethServices);
			
			List<Service> mbekeServices = new ArrayList<Service>();
			mbekeServices.add(movingService);
			mbeke.setServices(mbekeServices);
			
			List<Service> jcdServices = new ArrayList<Service>();
			jcdServices.add(training);
			jcdServices.add(strategy);
			jcd.setServices(jcdServices);
			
			List<Service> paulServices = new ArrayList<Service>();
			paulServices.add(organisation);
			paulServices.add(training);
			paul.setServices(paulServices);
			
			List<Service> walterServices = new ArrayList<Service>();
			walterServices.add(pestExt);
			walterServices.add(animal);
			walter.setServices(walterServices);
			
			List<Service> isabeauServices = new ArrayList<Service>();
			isabeauServices.add(pestExt);
			isabeauServices.add(animal);
			isabeau.setServices(isabeauServices);
			

			//upcoming workslot and bookingslot for schedule and available booking testing
			WorkSlot johnShift = new WorkSlot(day, shiftStartTime, shiftEndTime, john);
			BookingSlot johnSlot = new BookingSlot(day, bookingStartTime, bookingEndTime, johnServices);
			johnShift.addBookingSlot(johnSlot);
			
			//Tom likes to work a lot 
			WorkSlot tomShift0 = new WorkSlot(LocalDate.of(2022, 1, 2), LocalTime.of(12, 00), LocalTime.of(13,00), tom);
			BookingSlot tomSlot0 = new BookingSlot(LocalDate.of(2022, 1, 2), LocalTime.of(12, 00), LocalTime.of(13,00), tomServices);
			tomShift0.addBookingSlot(tomSlot0);
			WorkSlot tomShift1 = new WorkSlot(LocalDate.of(2022, 1, 2), LocalTime.of(14,00), LocalTime.of(15,00), tom);
			BookingSlot tomSlot1 = new BookingSlot(LocalDate.of(2022, 1, 2), LocalTime.of(14,00), LocalTime.of(15,00), tomServices);
			tomShift1.addBookingSlot(tomSlot1);
			
			WorkSlot scottShift0 = new WorkSlot(LocalDate.of(2022, 1, 2), LocalTime.of(9,00) , LocalTime.of(10,00), scott);
			BookingSlot scottSlot0 = new BookingSlot(LocalDate.of(2022, 1, 2), LocalTime.of(9,00) , LocalTime.of(10,00), scottServices);
			scottShift0.addBookingSlot(scottSlot0);
			
			WorkSlot sethShift0 = new WorkSlot(LocalDate.of(2022, 1, 2), LocalTime.of(1, 00), LocalTime.of(3, 00), seth);
			BookingSlot sethSlot0 = new BookingSlot(LocalDate.of(2022, 1, 2), LocalTime.of(1, 00), LocalTime.of(3, 00), sethServices);
			sethShift0.addBookingSlot(sethSlot0);
			
			WorkSlot mbekeShift0 = new WorkSlot(LocalDate.of(2022, 1, 10), LocalTime.of(9, 00), LocalTime.of(12, 00), mbeke);
			BookingSlot mbekeSlot0 = new BookingSlot(LocalDate.of(2022, 1, 10), LocalTime.of(9, 00), LocalTime.of(12, 00), mbekeServices);
			mbekeShift0.addBookingSlot(mbekeSlot0);
			
			WorkSlot jcdShift0 = new WorkSlot(LocalDate.of(2020, 12, 25), LocalTime.of(9, 00), LocalTime.of(12, 00), jcd);
			BookingSlot jcdSlot0 = new BookingSlot(LocalDate.of(2020, 12, 25), LocalTime.of(9, 00), LocalTime.of(12, 00), jcdServices);
			jcdShift0.addBookingSlot(jcdSlot0);
			
			WorkSlot paulShift0 = new WorkSlot(LocalDate.of(2021, 3, 22), LocalTime.of(15, 00), LocalTime.of(17, 00), paul);
			BookingSlot paulSlot0 = new BookingSlot(LocalDate.of(2021, 3, 22), LocalTime.of(15, 00), LocalTime.of(17, 00), paulServices);
			paulShift0.addBookingSlot(paulSlot0);
			
			WorkSlot walterShift0 = new WorkSlot(LocalDate.of(2021, 9, 22), LocalTime.of(15, 00), LocalTime.of(17, 00), walter);
			BookingSlot walterSlot0 = new BookingSlot(LocalDate.of(2021, 9, 22), LocalTime.of(15, 00), LocalTime.of(17, 00), walterServices);
			walterShift0.addBookingSlot(walterSlot0);
			
			WorkSlot isabeauShift0 = new WorkSlot(LocalDate.of(2021, 11, 7), LocalTime.of(12, 00), LocalTime.of(17, 00), isabeau);
			BookingSlot isabeauSlot0 = new BookingSlot(LocalDate.of(2021, 11, 7), LocalTime.of(12, 00), LocalTime.of(17, 00), isabeauServices);
			isabeauShift0.addBookingSlot(isabeauSlot0);
			
			WorkSlot isabeauShift1 = new WorkSlot(LocalDate.of(2020, 9, 26), LocalTime.of(12, 00), LocalTime.of(14, 00), isabeau);
			BookingSlot isabeauSlot1 = new BookingSlot(LocalDate.of(2020, 9, 26), LocalTime.of(12, 00), LocalTime.of(14, 00), isabeauServices);
			isabeauShift1.addBookingSlot(isabeauSlot1);
			
			// // //old shift and bookingslot for past booking/booking history testing
			WorkSlot oldShift = new WorkSlot(oldDay, shiftStartTime, shiftEndTime, john);
			WorkSlot oldShift2 = new WorkSlot(oldDay2, shiftStartTime, bookingStartTime, john);
			WorkSlot oldShift3 = new WorkSlot(oldDay3, bookingStartTime, shiftEndTime, john);
			BookingSlot oldSlot = new BookingSlot(oldDay, bookingStartTime, bookingEndTime, johnServices);
			BookingSlot oldSlot2 = new BookingSlot(oldDay2, shiftStartTime, bookingStartTime, johnServices);
			BookingSlot oldSlot3 = new BookingSlot(oldDay3, bookingStartTime, shiftEndTime, johnServices);
			oldShift.addBookingSlot(oldSlot);
			oldShift2.addBookingSlot(oldSlot2);
			oldShift3.addBookingSlot(oldSlot3);
	
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
			Booking austinBooking = new Booking(austin, john, barber, haircut, johnSlot);	
			Booking lukeBooking0 = new Booking(luke, scott, restaurant, birthday, scottSlot0);
			lukeBooking0.getBookingSlot().setBookedService(birthday);
			
			Booking hershelBooking0 = new Booking(hershel, tom, restaurant, reserveTable, tomSlot0);
			hershelBooking0.getBookingSlot().setBookedService(reserveTable);
			
			Booking hershelBooking1 = new Booking(hershel, isabeau, exterminator, animal, isabeauSlot1);
			hershelBooking1.getBookingSlot().setBookedService(animal);

			// //old bookings
			Booking oldBooking = new Booking(austin, john, barber, beardtrim, oldSlot);
			Booking oldBooking2 = new Booking(austin, john, barber, haircut, oldSlot2);
			Booking oldBooking3 = new Booking(austin, john, barber, beardtrim, oldSlot3);
			oldBooking.setStatusCompleted();
			oldBooking2.setStatusCompleted();
			oldBooking3.setStatusCompleted();

			//save everything to repos (do this at the end to avoid detach errors)
			businessRepository.save(barber);
			businessRepository.save(restaurant);
			businessRepository.save(delivery);
			businessRepository.save(consultancy);
			businessRepository.save(exterminator);
			
			adminRepository.save(caramel);
			adminRepository.save(puzzleboy);
			adminRepository.save(mandy);
			adminRepository.save(bob);
			adminRepository.save(flynn);
			
			workerRepository.save(john);
			workerRepository.save(jcd);
			workerRepository.save(tom);
			workerRepository.save(scott);
			workerRepository.save(seth);
			workerRepository.save(mbeke);
			workerRepository.save(jcd);
			workerRepository.save(paul);
			workerRepository.save(walter);
			workerRepository.save(isabeau);
			
			serviceRepository.save(haircut);
			serviceRepository.save(beardtrim);
			serviceRepository.save(reserveTable);
			serviceRepository.save(birthday);
			serviceRepository.save(foodShow);
			serviceRepository.save(movingService);
			serviceRepository.save(smuggling);
			serviceRepository.save(strategy);
			serviceRepository.save(organisation);
			serviceRepository.save(training);
			serviceRepository.save(pestExt);
			serviceRepository.save(animal);
			
			customerRepository.save(austin);
			customerRepository.save(stachenscarfen);
			customerRepository.save(luke);
			customerRepository.save(hershel);
			customerRepository.save(flora);
			
			workSlotRepository.save(johnShift);
			workSlotRepository.save(oldShift);
			workSlotRepository.save(oldShift2);
			workSlotRepository.save(oldShift3);
			workSlotRepository.save(newShift1);
			workSlotRepository.save(newShift2);
			bookingSlotRepository.save(johnSlot);
			bookingSlotRepository.save(oldSlot);
			bookingSlotRepository.save(newSlot1);
			bookingSlotRepository.save(newSlot2);			
			workSlotRepository.save(tomShift0);
			workSlotRepository.save(tomShift1);
			workSlotRepository.save(scottShift0);
			workSlotRepository.save(sethShift0);
			workSlotRepository.save(mbekeShift0);
			workSlotRepository.save(jcdShift0);
			workSlotRepository.save(paulShift0);
			workSlotRepository.save(walterShift0);
			workSlotRepository.save(isabeauShift0);
			workSlotRepository.save(isabeauShift1);
			
			bookingSlotRepository.save(johnSlot);
			bookingSlotRepository.save(oldSlot);
			bookingSlotRepository.save(oldSlot2);
			bookingSlotRepository.save(oldSlot3);	
			bookingSlotRepository.save(tomSlot0);
			bookingSlotRepository.save(tomSlot1);
			bookingSlotRepository.save(scottSlot0);
			bookingSlotRepository.save(sethSlot0);
			bookingSlotRepository.save(mbekeSlot0);
			bookingSlotRepository.save(jcdSlot0);
			bookingSlotRepository.save(paulSlot0);
			bookingSlotRepository.save(walterSlot0);
			bookingSlotRepository.save(isabeauSlot0);
			bookingSlotRepository.save(isabeauSlot1);

			bookingRepository.save(oldBooking);
			bookingRepository.save(oldBooking2);
			bookingRepository.save(oldBooking3);
			bookingRepository.save(austinBooking);
			bookingRepository.save(lukeBooking0);
			bookingRepository.save(hershelBooking0);
			bookingRepository.save(hershelBooking1);
			
		};
	}

}
