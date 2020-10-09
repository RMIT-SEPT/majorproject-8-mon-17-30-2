package com.rmit.sept.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rmit.sept.majorProject.dto.BookingSlotBlueprint;
import com.rmit.sept.majorProject.dto.BookingSlotSummary;
import com.rmit.sept.majorProject.model.BookingSlot;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Service;
import com.rmit.sept.majorProject.model.WorkSlot;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.repository.BookingSlotRepository;
import com.rmit.sept.majorProject.repository.BusinessRepository;
import com.rmit.sept.majorProject.repository.ServiceRepository;
import com.rmit.sept.majorProject.repository.WorkSlotRepository;
import com.rmit.sept.majorProject.repository.WorkerRepository;
import com.rmit.sept.majorProject.service.BookingSlotService;

@ExtendWith(SpringExtension.class)
public class BookingSlotServiceTest {
	
	@TestConfiguration
	static class BookingSlotServiceTestContextConfiguration{
		@Bean
		public BookingSlotService bookingSlotService() {
			return new  BookingSlotService();
		}
	}
	
	@Autowired
	private BookingSlotService bookingSlotService;
	@MockBean
	ServiceRepository serviceRepository;
	@MockBean
	BookingSlotRepository bookingSlotRepository;
	@MockBean
	WorkSlotRepository workSlotRepository;
	@MockBean
	BusinessRepository businessRepository;
	@MockBean
	WorkerRepository workerRepository;
	
	
	BookingSlotBlueprint bookingSlotBPTest;
	Iterable<Long> serviceIdsTest = new ArrayList<Long>();
	Iterable<Service> serviceListTest = new ArrayList<Service>();
	Service serviceTest0;
	Service serviceTest1;
	BookingSlot bookingSlotTest;
	WorkSlot workSlotTest;
	Worker workerTest;
	Business businessTest;
	
	@BeforeEach
	public void init() {
		serviceTest0 = new Service("Haircut","Cut hair", 2);
		serviceTest0.setId(1L);
		serviceTest1 = new Service("Beard Trim", "Trim the beard", 3);
		serviceTest0.setId(2L);
		((ArrayList<Long>)serviceIdsTest).add(serviceTest0.getId());
		((ArrayList<Long>)serviceIdsTest).add(serviceTest1.getId());
		((ArrayList<Service>) serviceListTest).add(serviceTest0);
		((ArrayList<Service>) serviceListTest).add(serviceTest1);
		businessTest = new Business("Busi Business");
		businessTest.setId(1L);
		workerTest = new Worker("worker","workerUser","workerPass","worker@email.com","0 Worker Street", "123456789");
		workerTest.setId(1L);
		workerTest.setBusiness(businessTest);
		bookingSlotBPTest = new BookingSlotBlueprint(1L, "2021-02-01", "12:00", "14:00", serviceIdsTest);
		bookingSlotTest = new BookingSlot(LocalDate.parse("2021-02-01"), LocalTime.parse("12:00"), LocalTime.parse("14:00"), (List<Service>)serviceListTest);
		bookingSlotTest.setId(1L);
		workSlotTest = new WorkSlot(LocalDate.parse("2021-02-01"), LocalTime.parse("12:00"), LocalTime.parse("14:00"), workerTest);
		workSlotTest.setId(1L);
		bookingSlotTest.setWorkSlot(workSlotTest);
	}
	
	//Add booking slot with valid details
	@Test
	public void testAddBookingSlot_ValidDetails() {
		BookingSlotService bookingSlotSpy = spy(bookingSlotService);
		when(serviceRepository.findById(serviceTest0.getId())).thenReturn(Optional.of(serviceTest0));
		when(serviceRepository.findById(serviceTest1.getId())).thenReturn(Optional.of(serviceTest1));
		when(workSlotRepository.findById(bookingSlotBPTest.getWorkSlotId())).thenReturn(Optional.of(workSlotTest));
		doReturn(false).when(bookingSlotSpy).bookingSlotOverlap(bookingSlotTest, null, bookingSlotBPTest.getWorkSlotId());
		when(bookingSlotRepository.save(bookingSlotTest)).thenReturn(bookingSlotTest);
		
		assertEquals(new BookingSlotSummary(bookingSlotTest), bookingSlotSpy.createNewBookingSlot(bookingSlotBPTest));
	}
	
	//Add booking slot with conflicting booking slot
	@Test
	public void testAddBookingSlot_ConflictingBookingSlot() {
		BookingSlotService bookingSlotSpy = spy(bookingSlotService);
		when(serviceRepository.findById(serviceTest0.getId())).thenReturn(Optional.of(serviceTest0));
		when(serviceRepository.findById(serviceTest1.getId())).thenReturn(Optional.of(serviceTest1));
		when(workSlotRepository.findById(bookingSlotBPTest.getWorkSlotId())).thenReturn(Optional.of(workSlotTest));
		doReturn(true).when(bookingSlotSpy).bookingSlotOverlap(bookingSlotTest, null, bookingSlotBPTest.getWorkSlotId());
		when(bookingSlotRepository.save(bookingSlotTest)).thenReturn(bookingSlotTest);
		
		Assertions.assertThrows(DuplicateKeyException.class, ()->{
			bookingSlotSpy.createNewBookingSlot(bookingSlotBPTest);
		});
	}
	
	//Add booking slot with invalid time
	@Test
	public void testAddBookingSlot_InvalidTime() {
		BookingSlotService bookingSlotSpy = spy(bookingSlotService);
		bookingSlotBPTest.setEndTime("08:30");
		bookingSlotBPTest.setStartTime("14:30");
		when(serviceRepository.findById(serviceTest0.getId())).thenReturn(Optional.of(serviceTest0));
		when(serviceRepository.findById(serviceTest1.getId())).thenReturn(Optional.of(serviceTest1));
		when(workSlotRepository.findById(bookingSlotBPTest.getWorkSlotId())).thenReturn(Optional.of(workSlotTest));
		doReturn(false).when(bookingSlotSpy).bookingSlotOverlap(bookingSlotTest, null, bookingSlotBPTest.getWorkSlotId());
		when(bookingSlotRepository.save(bookingSlotTest)).thenReturn(bookingSlotTest);
		Assertions.assertThrows(DataIntegrityViolationException.class, ()->{
			bookingSlotSpy.createNewBookingSlot(bookingSlotBPTest);
		});
	}
	
	//Add booking slot with invalid date
	@Test
	public void testAddBookingSlot_InvalidDate() {
		BookingSlotService bookingSlotSpy = spy(bookingSlotService);
		bookingSlotBPTest.setDate("1990-05-06");
		when(serviceRepository.findById(serviceTest0.getId())).thenReturn(Optional.of(serviceTest0));
		when(serviceRepository.findById(serviceTest1.getId())).thenReturn(Optional.of(serviceTest1));
		when(workSlotRepository.findById(bookingSlotBPTest.getWorkSlotId())).thenReturn(Optional.of(workSlotTest));
		doReturn(false).when(bookingSlotSpy).bookingSlotOverlap(bookingSlotTest, null, bookingSlotBPTest.getWorkSlotId());
		when(bookingSlotRepository.save(bookingSlotTest)).thenReturn(bookingSlotTest);
		Assertions.assertThrows(DataIntegrityViolationException.class, ()->{
			bookingSlotSpy.createNewBookingSlot(bookingSlotBPTest);
		});
	}
	
	//Delete booking slot with existing booking slot
	@Test
	public void testDeleteBookingSlot_ExistingBookingSlot() {
		BookingSlotService bookingSlotSpy = spy(bookingSlotService);
		doReturn(bookingSlotTest).when(bookingSlotSpy).findById(bookingSlotTest.getId());
		assertTrue(bookingSlotSpy.deleteBookingSlot(bookingSlotTest.getId()));
	}
	
	//Delete booking slot with non existent booking slot
	@Test
	public void testDeleteBookingSlot_NonExistingBookingSlot() {
		BookingSlotService bookingSlotSpy = spy(bookingSlotService);
		doReturn(null).when(bookingSlotSpy).findById(bookingSlotTest.getId());
		assertFalse(bookingSlotSpy.deleteBookingSlot(bookingSlotTest.getId()));
	}
	
	//Edit booking slot with valid details
	@Test
	public void testEditBookingSlot_ValidDetails() {
		BookingSlotBlueprint newBookingBP = bookingSlotBPTest;
		newBookingBP.setDate("2021-09-08");
		BookingSlot newBookingSlot = bookingSlotTest;
		newBookingSlot.setDate(LocalDate.parse("2021-09-08"));
		BookingSlotService bookingSlotSpy = spy(bookingSlotService);
		when(serviceRepository.findById(serviceTest0.getId())).thenReturn(Optional.of(serviceTest0));
		when(serviceRepository.findById(serviceTest1.getId())).thenReturn(Optional.of(serviceTest1));
		when(bookingSlotRepository.findById(bookingSlotTest.getId())).thenReturn(Optional.of(bookingSlotTest));
		when(workSlotRepository.findById(workSlotTest.getId())).thenReturn(Optional.of(workSlotTest));
		when(bookingSlotRepository.save(newBookingSlot)).thenReturn(newBookingSlot);
		doReturn(false).when(bookingSlotSpy).bookingSlotOverlap(bookingSlotTest, bookingSlotTest.getId(), workSlotTest.getId());
		assertEquals(new BookingSlotSummary(newBookingSlot), bookingSlotSpy.editBookingSlot(bookingSlotTest.getId(), newBookingBP));
	}
	
	//Edit booking slot with invalid time
	@Test
	public void testEditBookingSlot_InvalidTime() {
		BookingSlotBlueprint newBookingBP = bookingSlotBPTest;
		newBookingBP.setStartTime("08:30");
		newBookingBP.setEndTime("06:30");
		BookingSlot newBookingSlot = bookingSlotTest;
		newBookingSlot.setStartTime(LocalTime.parse("08:30"));
		newBookingSlot.setEndTime(LocalTime.parse("06:30"));
		BookingSlotService bookingSlotSpy = spy(bookingSlotService);
		when(serviceRepository.findById(serviceTest0.getId())).thenReturn(Optional.of(serviceTest0));
		when(serviceRepository.findById(serviceTest1.getId())).thenReturn(Optional.of(serviceTest1));
		when(bookingSlotRepository.findById(bookingSlotTest.getId())).thenReturn(Optional.of(bookingSlotTest));
		when(workSlotRepository.findById(workSlotTest.getId())).thenReturn(Optional.of(workSlotTest));
		when(bookingSlotRepository.save(newBookingSlot)).thenReturn(newBookingSlot);
		doReturn(false).when(bookingSlotSpy).bookingSlotOverlap(bookingSlotTest, bookingSlotTest.getId(), workSlotTest.getId());
		Assertions.assertThrows(DataIntegrityViolationException.class, ()->{
			bookingSlotSpy.editBookingSlot(bookingSlotTest.getId(), newBookingBP);
		});
	}
	
	//Edit booking slot with invalid date
	@Test
	public void testEditBookingSlot_InvalidDate() {
		BookingSlotBlueprint newBookingBP = bookingSlotBPTest;
		newBookingBP.setDate("1990-06-02");
		BookingSlot newBookingSlot = bookingSlotTest;
		newBookingSlot.setDate(LocalDate.parse("1990-06-02"));
		BookingSlotService bookingSlotSpy = spy(bookingSlotService);
		when(serviceRepository.findById(serviceTest0.getId())).thenReturn(Optional.of(serviceTest0));
		when(serviceRepository.findById(serviceTest1.getId())).thenReturn(Optional.of(serviceTest1));
		when(bookingSlotRepository.findById(bookingSlotTest.getId())).thenReturn(Optional.of(bookingSlotTest));
		when(workSlotRepository.findById(workSlotTest.getId())).thenReturn(Optional.of(workSlotTest));
		when(bookingSlotRepository.save(newBookingSlot)).thenReturn(newBookingSlot);
		doReturn(false).when(bookingSlotSpy).bookingSlotOverlap(bookingSlotTest, bookingSlotTest.getId(), workSlotTest.getId());
		Assertions.assertThrows(DataIntegrityViolationException.class, ()->{
			bookingSlotSpy.editBookingSlot(bookingSlotTest.getId(), newBookingBP);
		});
		
	}
	
}
