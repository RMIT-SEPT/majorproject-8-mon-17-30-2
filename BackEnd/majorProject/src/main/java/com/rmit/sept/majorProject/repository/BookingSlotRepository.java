package com.rmit.sept.majorProject.repository;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rmit.sept.majorProject.model.BookingSlot;

@Transactional
public interface BookingSlotRepository extends CrudRepository<BookingSlot, Long> {

    // Get newest bookings
    @Query(value = "SELECT TOP 1 * FROM booking_slot ORDER BY id ASC", nativeQuery = true)
    public BookingSlot getNewest();

    public Iterable<BookingSlot> findByDateAndStartTimeAndEndTime(LocalDate date, LocalTime startTime,
            LocalTime endTime);

    public Iterable<BookingSlot> findAllByWorkSlotId(Long workSlotId);

}