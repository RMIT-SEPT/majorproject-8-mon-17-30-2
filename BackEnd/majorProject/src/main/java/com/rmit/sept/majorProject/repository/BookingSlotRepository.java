package com.rmit.sept.majorProject.repository;

import com.rmit.sept.majorProject.model.Worker;
import org.springframework.transaction.annotation.Transactional;
import com.rmit.sept.majorProject.model.BookingSlot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface BookingSlotRepository extends CrudRepository<BookingSlot, Long> {
    
    //testing purposes
    @Query(value = "SELECT TOP 1 * FROM booking_slot ORDER BY id DESC", nativeQuery = true)
    public BookingSlot getNewest();

}