package com.rmit.sept.majorProject.repository;

import org.springframework.transaction.annotation.Transactional;
import com.rmit.sept.majorProject.model.BookingSlot;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface BookingSlotRepository extends CrudRepository<BookingSlot, Long> {
    
    //Get newest bookings 
    @Query(value = "SELECT TOP 1 * FROM booking_slot ORDER BY id ASC", nativeQuery = true)
    public BookingSlot getNewest();

    public Iterable<BookingSlot> findByDateAndStartTimeAndEndTime(LocalDate date, LocalTime startTime, LocalTime endTime);
    // @Query(value = "SELECT * FROM booking_slot WHERE ")
    // public Iterable<BookingSlot> getAllAvailable();

}