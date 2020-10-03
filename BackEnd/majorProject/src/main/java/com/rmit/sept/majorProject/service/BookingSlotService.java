package com.rmit.sept.majorProject.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.rmit.sept.majorProject.dto.BookingSlotBlueprint;
import com.rmit.sept.majorProject.dto.BookingSlotSummary;
import com.rmit.sept.majorProject.model.BookingSlot;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Service;
import com.rmit.sept.majorProject.model.WorkSlot;
import com.rmit.sept.majorProject.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.rmit.sept.majorProject.repository.BookingSlotRepository;
import com.rmit.sept.majorProject.repository.BusinessRepository;
import com.rmit.sept.majorProject.repository.ServiceRepository;
import com.rmit.sept.majorProject.repository.WorkSlotRepository;
import com.rmit.sept.majorProject.repository.WorkerRepository;

@org.springframework.stereotype.Service
public class BookingSlotService{

	@Autowired
	private BookingSlotRepository repository;
	@Autowired
	private WorkSlotRepository workSlotRepository;
	@Autowired
	private WorkerRepository workerRepository;
	@Autowired
	private ServiceRepository serviceRepository;
	@Autowired
	private BusinessRepository businessRepository;

    // return a list of all booking slot objects, whether or not they contain a booking
    public Iterable<BookingSlot> getAllBookingSlots(){
		return repository.findAll();
	}
	
	public BookingSlot findById(Long bookingSlotId){
		return repository.findById(bookingSlotId).get();
	}

    public Iterable<BookingSlotSummary> getAllBookingSlotsDTO(){
        ArrayList<BookingSlotSummary> bookingSlotDtos = new ArrayList<>();
		Iterable<BookingSlot> bookingSlots = getAllBookingSlots();
		for(BookingSlot bookingSlot : bookingSlots){
			bookingSlotDtos.add(new BookingSlotSummary(bookingSlot));
        }
        return bookingSlotDtos;
    }
    
    //return list of all bookingslots that either:
    // 1: are unset (no booking has been created, and thus no service has been "set")
    // 2: have vacancy (booking(s) have been created, service has been set, but the capacity hasn't been reached)
    public Iterable<BookingSlot> getAvailableBookingSlots(){
        ArrayList<BookingSlot> availableSlots = new ArrayList<BookingSlot>();
        for(BookingSlot slot : repository.findAll()){
            if(!slot.isSet() || slot.getBookings().size() < slot.getBookedService().getCapacity()){
                availableSlots.add(slot);
            }
        }        
        return availableSlots;
	}

	public Iterable<BookingSlotSummary> getAvailableBookingSlotsDTO(){
        ArrayList<BookingSlotSummary> availableSlotDtos = new ArrayList<BookingSlotSummary>();
        for(BookingSlot slot : getAvailableBookingSlots()){
            availableSlotDtos.add(new BookingSlotSummary(slot));
        }
        return availableSlotDtos;
	}
	
	public Iterable<BookingSlot> getAvailableBookingSlotsByBusiness(Long businessId){
        ArrayList<BookingSlot> availableSlots = new ArrayList<BookingSlot>();
        for(BookingSlot slot : repository.findAll()){
            if(slot.getWorkSlot().getWorker().getBusiness().getId() == businessId && (!slot.isSet() || slot.getBookings().size() < slot.getBookedService().getCapacity())){
                availableSlots.add(slot);
            }
        }        
        return availableSlots;
    }
	
	public Iterable<BookingSlotSummary> getAvailableBookingSlotsByBusinessDTO(Long businessId){
        ArrayList<BookingSlotSummary> availableSlotDtos = new ArrayList<BookingSlotSummary>();
        for(BookingSlot slot : getAvailableBookingSlotsByBusiness(businessId)){
            availableSlotDtos.add(new BookingSlotSummary(slot));
        }
        return availableSlotDtos;
    }

	public Iterable<BookingSlotSummary> searchAvailableBookingSlots(Long businessId, Long serviceId, Long workerId, LocalDate date) {

		Iterable<BookingSlotSummary> availableSlots = getAvailableBookingSlotsDTO();
		ArrayList<BookingSlotSummary> matchingSlots = new ArrayList<BookingSlotSummary>();

		Business business = businessId != null ? businessRepository.findById(businessId).get() : null;
		Service service = (serviceId == null || serviceId == 0) ? null : serviceRepository.findById(serviceId).get();
		Worker worker = (workerId == null || workerId == 0) ? null : workerRepository.findById(workerId).get();
		
		//If everything is null, return every available bookingSlot for the business
		if(business == null && service == null && worker == null && date == null){
			return getAvailableBookingSlotsByBusinessDTO(businessId);
		}

		// otherwise, add every bookingSlot that matches any of the non-null attributes
		for(BookingSlotSummary slot : availableSlots){
			
			// if service is specified and current slot in loop isn't "set", 
			// see if specified service is included in slot's list of available services
			ArrayList<Long> serviceIds = new ArrayList<Long>();
			if(serviceId != null){
				if(!slot.isSet()){				
					for(Service availableService : slot.getAvailableServices()){
						serviceIds.add(availableService.getId());
					}
				}
			}	

			// if any of the specified search parameters don't match, break the loop to ignore the current slot in loop
			if((businessId != null && businessId != slot.getBusinessId()) ||
				(service != null && slot.isSet() && serviceId != slot.getBookedService().getId()) ||
				(service != null && !slot.isSet() && !serviceIds.contains(serviceId)) ||
				(worker != null && workerId != slot.getWorkerId()) ||
				(date != null && date.compareTo(slot.getDate()) != 0)){
				continue;
			}

			matchingSlots.add(slot);

		}
		
		return matchingSlots;

	}

	public BookingSlot getNewest(){
		return repository.getNewest();
	}
		
	public Iterable<BookingSlotSummary> findByWorkSlotId(Long workSlotId){
		ArrayList<BookingSlotSummary> matchingSlots = new ArrayList<BookingSlotSummary>();
		for(BookingSlot slot : workSlotRepository.findById(workSlotId).get().getBookingSlots()){
			matchingSlots.add(new BookingSlotSummary(slot));
		}
		return matchingSlots;
	}

	public BookingSlotSummary createNewBookingSlot(BookingSlotBlueprint blueprint){

        LocalDate date = LocalDate.parse(blueprint.getDate());
        LocalTime startTime = LocalTime.parse(blueprint.getStartTime());
		LocalTime endTime = LocalTime.parse(blueprint.getEndTime());
		ArrayList<Service> services = new ArrayList<Service>();
		WorkSlot workSlot = workSlotRepository.findById(blueprint.getWorkSlotId()).get();
		for(Long serviceId : blueprint.getServiceIds()){
			services.add(serviceRepository.findById(serviceId).get());
		}

		BookingSlot bookingSlot = new BookingSlot(date, startTime, endTime, services);

		if(bookingSlotOverlap(bookingSlot, null, blueprint.getWorkSlotId())){
            throw new DuplicateKeyException("BookingSlot overlap on " + blueprint.getDate() + 
            " between " + blueprint.getStartTime() + " and " + blueprint.getEndTime());
		}

		workSlot.addBookingSlot(bookingSlot);
		return new BookingSlotSummary(this.repository.save(bookingSlot));
	}

	public BookingSlotSummary editBookingSlot(Long bookingSlotId, BookingSlotBlueprint blueprint) {
		BookingSlot bookingSlot = repository.findById(bookingSlotId).get();
		WorkSlot workSlot = workSlotRepository.findById(blueprint.getWorkSlotId()).get();

        LocalTime startTime = LocalTime.parse(blueprint.getStartTime());
		LocalTime endTime = LocalTime.parse(blueprint.getEndTime());
		ArrayList<Service> services = new ArrayList<Service>();
		for(Long serviceId : blueprint.getServiceIds()){
			services.add(serviceRepository.findById(serviceId).get());
		}
		BookingSlot newBookingSlot = new BookingSlot(bookingSlot.getDate(), startTime, endTime, services);

        // If found, update details
        if (bookingSlot != null) {
            if(bookingSlotOverlap(newBookingSlot, bookingSlotId, bookingSlot.getWorkSlot().getId())){
                throw new DuplicateKeyException("BookingSlot overlap on " + bookingSlot.getDate() + 
                " between " + newBookingSlot.getStartTime() + " and " + newBookingSlot.getEndTime());
            }              
            if (newBookingSlot.getStartTime() != null) {
                bookingSlot.setStartTime(newBookingSlot.getStartTime());
            }
            if (newBookingSlot.getEndTime() != null) {
                bookingSlot.setEndTime(newBookingSlot.getEndTime());
			}  
			if (newBookingSlot.getAvailableServices() != null){
				bookingSlot.setAvailableServices(services);
			}   
			workSlot.addBookingSlot(bookingSlot);            
        }

        return new BookingSlotSummary(this.repository.save(bookingSlot));
    }
    
	// checks if a bookingslot will overlap with other bookingslots inside the parent workslot
	public boolean bookingSlotOverlap(BookingSlot newSlot, Long newSlotId, Long workSlotId){
		WorkSlot workSlot = workSlotRepository.findById(workSlotId).get();
		try{			
			for(BookingSlotSummary existingSlot : findByWorkSlotId(workSlotId)){
				if((existingSlot.getStartTime().isBefore(newSlot.getEndTime())  &&
					newSlot.getStartTime().isBefore(existingSlot.getEndTime()))){
						if((newSlotId == null) ||
						(newSlotId != null && newSlotId != existingSlot.getId())){
							return true;
						}
				}
			}
			if(newSlot.getStartTime().isBefore(workSlot.getStartTime()) || newSlot.getEndTime().isAfter(workSlot.getEndTime())){
				return true;
			}
		}
		catch(NullPointerException e) {}			

		return false;
	}

}