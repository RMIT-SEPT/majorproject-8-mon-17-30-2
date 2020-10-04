package com.rmit.sept.majorProject.repository;

import java.time.LocalDate;
import java.util.Optional;

import com.rmit.sept.majorProject.model.WorkSlot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface WorkSlotRepository extends CrudRepository<WorkSlot, Long> {
    
    //testing purposes
    @Query(value = "SELECT TOP 1 * FROM work_slot ORDER BY id DESC", nativeQuery = true)
    public WorkSlot getNewest();

    public Iterable<WorkSlot> findByDate(LocalDate date);

    public Iterable<WorkSlot> findByBusinessId(Long businessId);

    public Iterable<WorkSlot> findByWorkerId(Long workerId);

    
}