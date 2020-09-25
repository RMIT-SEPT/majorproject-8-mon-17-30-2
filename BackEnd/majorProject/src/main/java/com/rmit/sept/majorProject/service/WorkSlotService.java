package com.rmit.sept.majorProject.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.rmit.sept.majorProject.dto.WorkSlotBlueprint;
import com.rmit.sept.majorProject.dto.WorkSlotSummary;
import com.rmit.sept.majorProject.model.BookingSlot;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.WorkSlot;
import com.rmit.sept.majorProject.model.Worker;
import com.rmit.sept.majorProject.model.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.rmit.sept.majorProject.repository.BookingSlotRepository;
import com.rmit.sept.majorProject.repository.WorkSlotRepository;

@org.springframework.stereotype.Service
public class WorkSlotService {

	//Services
    @Autowired
    private WorkerService workerService;
    @Autowired
    private BusinessService businessService;
	
    //Repositories
    @Autowired
    private WorkSlotRepository repository;
    @Autowired
    private BookingSlotRepository bookingSlotRepository;
    
    
    public WorkSlotSummary addWorkSlot(WorkSlotBlueprint booking) {
    	Worker worker = workerService.findById(booking.getWorkerId()).get();
    	WorkSlot workSlot = new WorkSlot(booking.getDate(), booking.getStartTime(), booking.getEndTime(), worker);
    	
    	BookingSlot bookingSlot = new BookingSlot(booking.getDate(), booking.getStartTime(), booking.getEndTime(), (List<Service>) worker.getServices());
    	workSlot.addBookingSlot(bookingSlot);
    	
    	bookingSlotRepository.save(bookingSlot);
    	
    	return new WorkSlotSummary(repository.save(workSlot));
    }
    
    
    // return a list of all booking slot objects, whether or not they contain a
    // booking
    public Iterable<WorkSlot> getAllWorkSlots() {
        return repository.findAll();
    }

    public Iterable<WorkSlotSummary> getAllWorkSlotsDTO() {
        ArrayList<WorkSlotSummary> workSlotDtos = new ArrayList<>();
        Iterable<WorkSlot> workSlots = getAllWorkSlots();
        for (WorkSlot workSlot : workSlots) {
            workSlotDtos.add(new WorkSlotSummary(workSlot));
        }
        return workSlotDtos;
    }

    public Iterable<WorkSlot> findByWorkerId(Long workerId) {
        return repository.findByWorkerId(workerId);
    }

    public Iterable<WorkSlotSummary> findByWorkerIdDTO(Long workerId) {
        ArrayList<WorkSlotSummary> workSlotDtos = new ArrayList<WorkSlotSummary>();
        for (WorkSlot workSlot : findByWorkerId(workerId)) {
            workSlotDtos.add(new WorkSlotSummary(workSlot));
        }
        return workSlotDtos;
    }

    public Iterable<WorkSlotSummary> findByWorkerIdAndDateDTO(Long workerId, String dateString){
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ArrayList<WorkSlotSummary> workSlotDtos = new ArrayList<WorkSlotSummary>();
        for(WorkSlot workSlot : findByWorkerId(workerId)){
            if(workSlot.getDate().equals(date)){
                workSlotDtos.add(new WorkSlotSummary(workSlot));
            }            
        }
        return workSlotDtos;
    }

    public Iterable<WorkSlot> findByDate(LocalDate date){
        return repository.findByDate(date);
    }

    public WorkSlot getNewest() {
        return repository.getNewest();
    }

    public WorkSlotSummary editWorkSlot(Long workerId, WorkSlot newWorkSlot) {
        // Get a LIST of 'workSlots' for the parsed in worker ID
        Iterable<WorkSlot> workerSlots = this.findByWorkerId(workerId);
        // // Search LIST to find target slot to edit based on source ID
        WorkSlotSummary summary = null;
        for (WorkSlot workSlot : workerSlots) {
            if (workSlot.getId() == newWorkSlot.getId()) {
                // If found, update details
                if (newWorkSlot.getDate() != null) {
                    workSlot.setDate(newWorkSlot.getDate());
                }
                if (newWorkSlot.getStartTime() != null) {
                    workSlot.setStartTime(newWorkSlot.getStartTime());
                }
                if (newWorkSlot.getEndTime() != null) {
                    workSlot.setEndTime(newWorkSlot.getEndTime());
                }
                repository.save(workSlot);
                summary = new WorkSlotSummary(workSlot);
            }
        }

        return summary;

        // Different working way, but doesnt check base off given worker ID, top version should be better..

        // Optional<WorkSlot> workSlotOptional =
        // repository.findById(newWorkSlot.getId());
        // WorkSlot workSlotFound = workSlotOptional.get();
        // if (workSlotFound != null) {
        // if(newWorkSlot.getDate() != null) {
        // workSlotFound.setDate(newWorkSlot.getDate());
        // }
        // if(newWorkSlot.getStartTime() != null) {
        // workSlotFound.setStartTime(newWorkSlot.getStartTime());
        // }
        // if(newWorkSlot.getEndTime() != null) {
        // workSlotFound.setEndTime(newWorkSlot.getEndTime());
        // }

        // }
        // repository.save(workSlotFound);
        // return new WorkSlotSummary(workSlotFound);
    }

}
