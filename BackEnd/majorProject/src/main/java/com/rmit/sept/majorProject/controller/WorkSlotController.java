package com.rmit.sept.majorProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rmit.sept.majorProject.dto.WorkSlotSummary;
import com.rmit.sept.majorProject.model.WorkSlot;
import com.rmit.sept.majorProject.service.WorkSlotService;
<<<<<<< HEAD
@CrossOrigin(origins = "http://localhost:3000")
// @CrossOrigin(origins = "http://agmemonday2.com.s3-website-us-east-1.amazonaws.com")
=======
import com.rmit.sept.majorProject.Util;

@CrossOrigin(origins = Util.API_HOST)
>>>>>>> develop
@RestController
public class WorkSlotController {

	@Autowired
	private WorkSlotService workSlotService;
	
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
        Iterable<WorkSlotSummary> matchingWorkSlots = workSlotService.findByWorkerIdDTO(workerId);
		//if matching bookings are found return them and Status.OK, if none, return empty list and Status.NO_CONTENT
		return new ResponseEntity<>(matchingWorkSlots, matchingWorkSlots.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/api/worker/{workerId}/work-slots/{date}")
    public ResponseEntity<?> getWorkSlotsByWorkerIdAndDate(@PathVariable Long workerId, @PathVariable String date){
        Iterable<WorkSlotSummary> matchingWorkSlots = workSlotService.findByWorkerIdAndDateDTO(workerId, date);
		//if matching bookings are found return them and Status.OK, if none, return empty list and Status.NO_CONTENT
		return new ResponseEntity<>(matchingWorkSlots, matchingWorkSlots.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/api/worker/{workerId}/work-slots/edit")
    public ResponseEntity<?> updateWorkSlot(@RequestBody WorkSlot newWorkSlot, @PathVariable Long workerId){
		WorkSlotSummary exist = workSlotService.editWorkSlot(workerId ,newWorkSlot);
		return new ResponseEntity<>(exist, exist != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/api/business/{businessId}/work-slots/")
    public ResponseEntity<?> getWorkSlotsByBusiness(@PathVariable Long businessId){
        Iterable<WorkSlotSummary> matchingWorkSlots = workSlotService.findByBusinessIdDTO(businessId);
		//if matching bookings are found return them and Status.OK, if none, return empty list and Status.NO_CONTENT
		return new ResponseEntity<>(matchingWorkSlots, matchingWorkSlots.iterator().hasNext() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

}
