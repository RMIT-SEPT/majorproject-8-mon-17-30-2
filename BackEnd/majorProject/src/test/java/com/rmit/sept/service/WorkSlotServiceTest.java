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
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rmit.sept.majorProject.dto.WorkSlotBlueprint;
import com.rmit.sept.majorProject.dto.WorkSlotSummary;
import com.rmit.sept.majorProject.model.BookingSlot;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.WorkSlot;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.repository.BookingSlotRepository;
import com.rmit.sept.majorProject.repository.WorkSlotRepository;
import com.rmit.sept.majorProject.service.BusinessService;
import com.rmit.sept.majorProject.service.ServiceService;
import com.rmit.sept.majorProject.service.WorkSlotService;
import com.rmit.sept.majorProject.service.WorkerService;

@ExtendWith(SpringExtension.class)
public class WorkSlotServiceTest {
	
	@TestConfiguration
	static class WorkSlotServiceTestContextConfiguration{
		@Bean
		public WorkSlotService workSlotService() {
			return new WorkSlotService();
		}
	}
	
	@Autowired
	private WorkSlotService workSlotService;
	@MockBean
	private WorkerService workerService;
	@MockBean
	private BusinessService businessService;
	@MockBean
	private ServiceService serviceService;
	@MockBean
    private WorkSlotRepository workSlotRepository;
	@MockBean
    private BookingSlotRepository bookingSlotRepository;
	
	WorkSlotBlueprint workSlotBPTest;
	WorkSlot workSlotTest;
	WorkSlot newWorkSlotTest;
	Worker workerTest;
	Business businessTest;
	BookingSlot bookingSlotTest0;
	BookingSlot bookingSlotTest1;
	Iterable<BookingSlot> bookingSlotList = new ArrayList<BookingSlot>();
	
	
	@BeforeEach
	public void init() {
		workSlotBPTest = new WorkSlotBlueprint(1L, 3L, "2021-12-03", "12:30", "14:30");
		workerTest = new Worker("worker","workerUser","workerPass","worker@email.com","0 Worker Street", "123456789");
		workerTest.setId(1L);
		businessTest = new Business("Busi Business");
		businessTest.setId(3L);
		workerTest.setBusiness(businessTest);
		workSlotTest = new WorkSlot(LocalDate.parse("2021-12-03"),LocalTime.parse("12:30"), LocalTime.parse("14:30"),workerTest);
		workSlotTest.setId(1L);
		bookingSlotTest0 = new BookingSlot(LocalDate.parse("2021-12-03"),LocalTime.parse("12:30"), LocalTime.parse("14:30"), null);
		bookingSlotTest1 = new BookingSlot(LocalDate.parse("2021-12-05"),LocalTime.parse("12:30"), LocalTime.parse("14:30"), null);
		((ArrayList<BookingSlot>) bookingSlotList).add(bookingSlotTest0);
		((ArrayList<BookingSlot>) bookingSlotList).add(bookingSlotTest1);
		
	}
	
	//Add work slot with valid details
	@Test
	public void testAddWorkSlot_ValidDetails() {
		when(workerService.findById(workerTest.getId())).thenReturn(Optional.of(workerTest));
		when(businessService.findById(businessTest.getId())).thenReturn(businessTest);
		when(workSlotRepository.save(workSlotTest)).thenReturn(workSlotTest);
		assertEquals(new WorkSlotSummary(workSlotTest), workSlotService.createNewWorkSlot(workSlotBPTest));
	}
	
	//Add work slot with invalid worker
	@Test
	public void testAddWorkSlot_InvalidWorker() {
		when(workerService.findById(workerTest.getId())).thenReturn(Optional.empty());
		when(businessService.findById(businessTest.getId())).thenReturn(businessTest);
		when(workSlotRepository.save(workSlotTest)).thenReturn(workSlotTest);
		Assertions.assertThrows(NoSuchElementException.class, () -> {
			workSlotService.createNewWorkSlot(workSlotBPTest);
		  });
	}
	
	//Add work slot with invalid business
	@Test
	public void testAddWorkSlot_InvalidBusiness() {
		when(workerService.findById(workerTest.getId())).thenReturn(Optional.of(workerTest));
		when(businessService.findById(businessTest.getId())).thenReturn(null);
		when(workSlotRepository.save(workSlotTest)).thenReturn(workSlotTest);
		Assertions.assertThrows(DataRetrievalFailureException.class, () -> {
			workSlotService.createNewWorkSlot(workSlotBPTest);
		  });
	}
	
	//Add work slot with an existing work slot that it is in conflict in terms of time
	@Test
	public void testAddWorkSlot_Overlap() {
		WorkSlotService workSlotSpy = spy(workSlotService);
		when(workerService.findById(workerTest.getId())).thenReturn(Optional.of(workerTest));
		when(businessService.findById(businessTest.getId())).thenReturn(businessTest);
		when(workSlotRepository.save(workSlotTest)).thenReturn(workSlotTest);
		doReturn(true).when(workSlotSpy).workSlotOverlap(workSlotTest, null, workerTest.getId());
		Assertions.assertThrows(DuplicateKeyException.class, () -> {
			workSlotSpy.createNewWorkSlot(workSlotBPTest);
		  });	
	}
	
	//Delete an existing workslot in the database
	@Test
	public void testDeleteWorkSlot_ExistingWorkSlot() {
		WorkSlotService workSlotSpy = spy(workSlotService);
		doReturn(workSlotTest).when(workSlotSpy).findById(workSlotTest.getId());
		when(bookingSlotRepository.findAllByWorkSlotId(workerTest.getId())).thenReturn(bookingSlotList);
		assertTrue(workSlotSpy.deleteWorkSlot(workSlotTest.getId()));
	}
	
	//Delete a non-existing workslot in the database
	@Test
	public void testDeleteWorkSlot_NonExistingWorkSlot() {
		WorkSlotService workSlotSpy = spy(workSlotService);
		doReturn(null).when(workSlotSpy).findById(workSlotTest.getId());
		when(bookingSlotRepository.findAllByWorkSlotId(workerTest.getId())).thenReturn(bookingSlotList);
		assertFalse(workSlotSpy.deleteWorkSlot(workSlotTest.getId()));
	}
	
	//Delete an existing workslot in the database with no booking slot 
	@Test
	public void testDeleteWorkSlot_NoBookingSlot() {
		WorkSlotService workSlotSpy = spy(workSlotService);
		doReturn(workSlotTest).when(workSlotSpy).findById(workSlotTest.getId());
		when(bookingSlotRepository.findAllByWorkSlotId(workerTest.getId())).thenReturn(null);
		assertTrue(workSlotSpy.deleteWorkSlot(workSlotTest.getId()));
	}
	
	//Edit a work slot with valid details
	@Test
	public void testEditWorkSlot_ValidDetails() {
		WorkSlotService workSlotSpy = spy(workSlotService);
		newWorkSlotTest = new WorkSlot(LocalDate.parse("2022-12-03"),LocalTime.parse("10:30"), LocalTime.parse("15:30"),workerTest);
		newWorkSlotTest.setId(1L);
		when(workSlotRepository.findById(workSlotTest.getId())).thenReturn(Optional.of(workSlotTest));
		doReturn(false).when(workSlotSpy).workSlotOverlap(newWorkSlotTest, workSlotTest.getId(), workerTest.getId());
		doReturn(false).when(workSlotSpy).bookingSlotOverlap(newWorkSlotTest, workSlotTest);
		assertEquals(new WorkSlotSummary(newWorkSlotTest), workSlotSpy.editWorkSlot(workSlotTest.getId(), newWorkSlotTest));		
	}
	
	//Edit a work slot with no details changed
	@Test
	public void testEditWorkSlot_NoChangedDetails() {
		WorkSlotService workSlotSpy = spy(workSlotService);
		newWorkSlotTest = workSlotTest;
		when(workSlotRepository.findById(workSlotTest.getId())).thenReturn(Optional.of(workSlotTest));
		doReturn(false).when(workSlotSpy).workSlotOverlap(newWorkSlotTest, workSlotTest.getId(), workerTest.getId());
		doReturn(false).when(workSlotSpy).bookingSlotOverlap(newWorkSlotTest, workSlotTest);
		assertEquals(new WorkSlotSummary(workSlotTest), workSlotSpy.editWorkSlot(workSlotTest.getId(), newWorkSlotTest));		
	}
	
	//Edit a work slot with work slot overlap
//	@Test
//	public void 
}
