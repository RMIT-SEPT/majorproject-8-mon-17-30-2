package com.rmit.sept.majorProject.service;

import java.time.LocalDate;
import java.util.ArrayList;
import com.rmit.sept.majorProject.dto.WorkSlotSummary;
import com.rmit.sept.majorProject.model.WorkSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rmit.sept.majorProject.repository.WorkSlotRepository;

@Service
public class WorkSlotService{

	@Autowired
    private WorkSlotRepository repository;

    // return a list of all booking slot objects, whether or not they contain a booking
    public Iterable<WorkSlot> getAllWorkSlots(){
		return repository.findAll();
    }

    public Iterable<WorkSlotSummary> getAllWorkSlotsDTO(){
        ArrayList<WorkSlotSummary> workSlotDtos = new ArrayList<>();
		Iterable<WorkSlot> workSlots = getAllWorkSlots();
		for(WorkSlot workSlot : workSlots){
			workSlotDtos.add(new WorkSlotSummary(workSlot));
        }
        return workSlotDtos;
    }

    public Iterable<WorkSlot> findByWorkerId(Long workerId){
        return repository.findByWorkerId(workerId);
    }
    
    public Iterable<WorkSlotSummary> findByWorkerIdDTO(Long workerId){
        ArrayList<WorkSlotSummary> workSlotDtos = new ArrayList<WorkSlotSummary>();
        for(WorkSlot workSlot : findByWorkerId(workerId)){
            workSlotDtos.add(new WorkSlotSummary(workSlot));
        }
        return workSlotDtos;
    }

    public Iterable<WorkSlot> findByDate(LocalDate date){
        return repository.findByDate(date);
    }

	public WorkSlot getNewest(){
		return repository.getNewest();
	}

}
