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
import com.rmit.sept.majorProject.repository.WorkerRepository;

@org.springframework.stereotype.Service
public class BookingSlotService{

	@Autowired
    private BookingSlotRepository repository;
	@Autowired
	private WorkerRepository workerRepository;

    // return a list of all booking slot objects, whether or not they contain a booking
    public Iterable<BookingSlot> getAllBookingSlots(){
		return repository.findAll();
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

	public Iterable<BookingSlotSummary> searchAvailableBookingSlots(Business business, Worker worker, LocalDate date, Service service) {
		ArrayList<BookingSlotSummary> availableSlots = new ArrayList<BookingSlotSummary>();
		
		//If everything is null
		if(business == null && worker == null && date == null && service == null)
		{
			return availableSlots;
		}
		//If none are null
		if(worker != null && date != null && business != null && service != null)
		{
			for(BookingSlot slot : getAvailableBookingSlots()) {
				try {
					if(slot.getWorkSlot().getWorker().getBusiness().getId() == business.getId()
					&& slot.getWorkSlot().getWorker() == workerRepository.findByUsername(worker.getUsername())
					&& slot.getDate().isEqual(date)
					&& slot.searchServiceExist(service.getTitle())) 
						{
							availableSlots.add(new BookingSlotSummary(slot));
						}
				}
				catch(NullPointerException e){}
			}
		}
		//If business is not null
		else if(business != null)
		{
			if(worker == null && date == null && service == null)
			{
				for(BookingSlot slot : getAvailableBookingSlots()) {
					try {
						if(slot.getWorkSlot().getWorker().getBusiness().getId() == business.getId())
							{
								availableSlots.add(new BookingSlotSummary(slot));
							}
					}
					catch(NullPointerException e){}
				}
			}
			else if(date == null && service == null)
			{
				for(BookingSlot slot : getAvailableBookingSlots()) {
					try {
						if(slot.getWorkSlot().getWorker().getBusiness().getId() == business.getId()
								&& slot.getWorkSlot().getWorker() == workerRepository.findByUsername(worker.getUsername()))
							{
								availableSlots.add(new BookingSlotSummary(slot));
							}
					}
					catch(NullPointerException e){}
				}
			}
			else if(worker == null && service == null)
			{
				for(BookingSlot slot : getAvailableBookingSlots()) {
					try {
						if(slot.getWorkSlot().getWorker().getBusiness().getId() == business.getId() 
								&& slot.getDate().isEqual(date))
							{
								availableSlots.add(new BookingSlotSummary(slot));
							}
						}
					catch(NullPointerException e){}
				}
			}
			else if(date == null && worker == null)
			{
				for(BookingSlot slot : getAvailableBookingSlots()) {
					try {
						if(slot.getWorkSlot().getWorker().getBusiness().getId() == business.getId() 
								&& slot.searchServiceExist(service.getTitle()))
						{
							availableSlots.add(new BookingSlotSummary(slot));
						}
					}
					catch(NullPointerException e) {}
				}
			}
			else if(date == null)
			{
				for(BookingSlot slot : getAvailableBookingSlots()) {
					try {
						if(slot.getWorkSlot().getWorker().getBusiness().getId() == business.getId() 
								&& slot.searchServiceExist(service.getTitle())
								&& slot.getWorkSlot().getWorker() == workerRepository.findByUsername(worker.getUsername()))
						{
							availableSlots.add(new BookingSlotSummary(slot));
						}
					}
					catch(NullPointerException e) {}
				}
			}
			
			else if(worker == null)
			{
				for(BookingSlot slot : getAvailableBookingSlots()) {
					try {
						if(slot.getWorkSlot().getWorker().getBusiness().getId() == business.getId() 
								&& slot.searchServiceExist(service.getTitle())
								&& slot.getDate().isEqual(date))
						{
							availableSlots.add(new BookingSlotSummary(slot));
						}
					}
					catch(NullPointerException e) {}
				}
			}
			else if(service == null)
			{
				for(BookingSlot slot : getAvailableBookingSlots()) {
					try {
						if(slot.getWorkSlot().getWorker().getBusiness().getId() == business.getId()
						&& slot.getWorkSlot().getWorker() == workerRepository.findByUsername(worker.getUsername())
						&& slot.getDate().isEqual(date)) 
							{
								availableSlots.add(new BookingSlotSummary(slot));
							}
					}
					catch(NullPointerException e){}
				}
			}
		}
		//If worker is not null
		else if(worker != null)
		{
			if(business == null && date == null && service == null)
			{
				for(BookingSlot slot : getAvailableBookingSlots()) {
					try {
						if(slot.getWorkSlot().getWorker() == workerRepository.findByUsername(worker.getUsername()))
						{
							availableSlots.add(new BookingSlotSummary(slot));
						}
					}
					catch(NullPointerException e){}
				}
			}
			else if(business == null && service == null)
			{
				for(BookingSlot slot : getAvailableBookingSlots()) {
					try {
						if(slot.getDate().isEqual(date) 
								&& slot.getWorkSlot().getWorker() == workerRepository.findByUsername(worker.getUsername()))
						{
							availableSlots.add(new BookingSlotSummary(slot));
						}
					}
					catch(NullPointerException e){}
				}
			}
			else if(business == null && date == null)
			{
				for(BookingSlot slot : getAvailableBookingSlots()) {
					try {
						if(slot.searchServiceExist(service.getTitle())
								&& slot.getWorkSlot().getWorker() == workerRepository.findByUsername(worker.getUsername()))
						{
							availableSlots.add(new BookingSlotSummary(slot));
						}
					}
					catch(NullPointerException e){}
				}
			}
			else if(business == null)
			{
				for(BookingSlot slot : getAvailableBookingSlots()) {
					try {
						if(slot.searchServiceExist(service.getTitle())
								&& slot.getWorkSlot().getWorker() == workerRepository.findByUsername(worker.getUsername())
								&& slot.getDate().isEqual(date))
						{
							availableSlots.add(new BookingSlotSummary(slot));
						}
					}
					catch(NullPointerException e){}
				}
			}
		}
		//If date is not null
		else if(date != null)
		{
			if(business == null && worker == null && service == null)
			{
				for(BookingSlot slot : getAvailableBookingSlots())
				{
					try {
						if(slot.getDate().isEqual(date))
						{
							availableSlots.add(new BookingSlotSummary(slot));
						}
					}
					catch(NullPointerException e){}
				}
			}
			else if(business == null && worker == null)
			{
				for(BookingSlot slot : getAvailableBookingSlots())
				{
					try {
						if(slot.getDate().isEqual(date)
								&& slot.searchServiceExist(service.getTitle()))
						{
							availableSlots.add(new BookingSlotSummary(slot));
						}
					}
					catch(NullPointerException e){}
				}
			}
		}
		else if(service != null)
		{
			if(business == null && worker == null && date == null)
			{
				for(BookingSlot slot : getAvailableBookingSlots())
				{
					try {
						if(slot.searchServiceExist(service.getTitle()))
						{
							availableSlots.add(new BookingSlotSummary(slot));
						}
					}
					catch(NullPointerException e){}
				}
			}
		}
		return availableSlots;
	}

	public BookingSlot getNewest(){
		return repository.getNewest();
	}

}
