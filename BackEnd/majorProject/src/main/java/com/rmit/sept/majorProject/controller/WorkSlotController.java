package com.rmit.sept.majorProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rmit.sept.majorProject.dto.WorkSlotBlueprint;
import com.rmit.sept.majorProject.dto.WorkSlotSummary;
import com.rmit.sept.majorProject.model.WorkSlot;
import com.rmit.sept.majorProject.service.WorkSlotService;
import javax.validation.Valid;
import com.rmit.sept.majorProject.utility.Util;

@CrossOrigin(origins = Util.API_HOST)
@RestController
public class WorkSlotController {

	@Autowired
	private WorkSlotService workSlotService;

	@PostMapping("/api/work-slot")
	public ResponseEntity<?> addNewWorkSlot(@Valid @RequestBody WorkSlotBlueprint blueprint){
		WorkSlotSummary workslot;
    	try {
    		workslot = this.workSlotService.createNewWorkSlot(blueprint);
    	}
    	catch(DuplicateKeyException DkEx) {
    		return new ResponseEntity<String>(DkEx.getMessage(), HttpStatus.BAD_REQUEST);
    	}
    	catch(DataIntegrityViolationException DIVEx) {
    		return new ResponseEntity<String>(DIVEx.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    	}
    	return new ResponseEntity<>(workslot, HttpStatus.CREATED);
	}

	@GetMapping("/api/work-slot/{workSlotId}")
	public WorkSlotSummary getWorkSlotById(@PathVariable Long workSlotId){
		if(workSlotId <= 0 || workSlotId == null) {
			return null;
		}
		return new WorkSlotSummary(workSlotService.findById(workSlotId));
	}
	
	@GetMapping("/api/work-slot")
	public Iterable<WorkSlotSummary> getAllWorkSlotsDTO() {
		return workSlotService.getAllWorkSlotsDTO();
	}

	@GetMapping("/api/work-slot/newest")
	public WorkSlotSummary getNewest(){
		return new WorkSlotSummary(workSlotService.getNewest());
    }
    
    @GetMapping("/api/worker/{workerId}/work-slots")
    public ResponseEntity<?> getWorkSlotsByWorkerId(@PathVariable Long workerId){
		if(workerId == null || workerId <= 0){
			return new ResponseEntity<String>("Invalid Worker ID", HttpStatus.BAD_REQUEST);
		}
        Iterable<WorkSlotSummary> matchingWorkSlots = workSlotService.findByWorkerIdDTO(workerId);
		//if matching bookings are found return them and Status.OK, if none, return empty list and Status.NO_CONTENT
		return new ResponseEntity<>(matchingWorkSlots, matchingWorkSlots.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/api/worker/{workerId}/work-slots/{date}")
    public ResponseEntity<?> getWorkSlotsByWorkerIdAndDate(@PathVariable String workerId, @PathVariable String date){
		if(workerId == null || date == null){
			return new ResponseEntity<String>("Invalid values passed", HttpStatus.BAD_REQUEST);
		}
		Iterable<WorkSlotSummary> matchingWorkSlots = workSlotService.findByWorkerIdAndDateDTO(Long.parseLong(workerId), date);
		//if matching bookings are found return them and Status.OK, if none, return empty list and Status.NO_CONTENT
		return new ResponseEntity<>(matchingWorkSlots, matchingWorkSlots.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/api/work-slot/{workSlotId}")
    public ResponseEntity<?> updateWorkSlot(@Valid @RequestBody WorkSlot newWorkSlot, @PathVariable Long workSlotId){
		if(workSlotId == null || workSlotId <= 0){
			return new ResponseEntity<String>("Invalid work slot ID", HttpStatus.BAD_REQUEST);
		}
		WorkSlotSummary workslot;
    	try {
    		workslot = workSlotService.editWorkSlot(workSlotId, newWorkSlot);
    	}
    	catch(DuplicateKeyException DkEx) {
    		return new ResponseEntity<String>(DkEx.getMessage(), HttpStatus.BAD_REQUEST);
    	}
    	catch(DataIntegrityViolationException DIVEx) {
    		return new ResponseEntity<String>(DIVEx.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    	}
    	return new ResponseEntity<>(workslot, HttpStatus.CREATED);
    }

	@DeleteMapping(value = "/api/work-slot/{workSlotId}")
	public ResponseEntity<?> deleteWorkSlot(@PathVariable Long workSlotId) {
		if(workSlotId == null || workSlotId <= 0){
			return new ResponseEntity<String>("Invalid work slot ID", HttpStatus.BAD_REQUEST);
		}
		boolean isRemoved = workSlotService.deleteWorkSlot(workSlotId);

		if (!isRemoved) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(workSlotId, HttpStatus.OK);
	}
}
