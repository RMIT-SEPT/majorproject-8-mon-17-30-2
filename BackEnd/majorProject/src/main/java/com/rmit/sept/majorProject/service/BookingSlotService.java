package com.rmit.sept.majorProject.service;

import java.time.LocalDate;
import java.util.ArrayList;
import com.rmit.sept.majorProject.dto.BookingSlotSummary;
import com.rmit.sept.majorProject.model.BookingSlot;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.Service;
import com.rmit.sept.majorProject.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import com.rmit.sept.majorProject.repository.BookingSlotRepository;
import com.rmit.sept.majorProject.repository.BusinessRepository;
import com.rmit.sept.majorProject.repository.ServiceRepository;
import com.rmit.sept.majorProject.repository.WorkerRepository;

@org.springframework.stereotype.Service
public class BookingSlotService{

	@Autowired
    private BookingSlotRepository repository;
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

}