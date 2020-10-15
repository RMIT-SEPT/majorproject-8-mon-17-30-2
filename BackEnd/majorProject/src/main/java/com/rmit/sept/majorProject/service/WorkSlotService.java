package com.rmit.sept.majorProject.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.DuplicateKeyException;

import com.rmit.sept.majorProject.model.BookingSlot;
import com.rmit.sept.majorProject.repository.BookingSlotRepository;
import com.rmit.sept.majorProject.repository.WorkSlotRepository;
import com.rmit.sept.majorProject.dto.WorkSlotSummary;
import com.rmit.sept.majorProject.dto.WorkSlotBlueprint;
import com.rmit.sept.majorProject.model.Business;
import com.rmit.sept.majorProject.model.WorkSlot;
import com.rmit.sept.majorProject.model.Worker;

@org.springframework.stereotype.Service
public class WorkSlotService {

    // Services
    @Autowired
    private WorkerService workerService;
    @Autowired
    private BusinessService businessService;

    // Repositories
    @Autowired
    private WorkSlotRepository repository;
    @Autowired
    private BookingSlotRepository bookingSlotRepository;

    // return a list of all work slot objects, whether or not they contain a
    // booking
    public Iterable<WorkSlot> getAllWorkSlots() {
        return repository.findAll();
    }

    public Iterable<WorkSlotSummary> getAllWorkSlotsDTO() {
        ArrayList<WorkSlotSummary> workSlotDtos = new ArrayList<>();
        Iterable<WorkSlot> workSlots = getAllWorkSlots();
        for (WorkSlot workSlot : workSlots) {
            workSlotDtos.add(new WorkSlotSummary(workSlot));
        }
        return workSlotDtos;
    }

    public Iterable<WorkSlot> findByWorkerId(Long workerId) {
        return repository.findByWorkerId(workerId);
    }

    public Iterable<WorkSlotSummary> findByWorkerIdDTO(Long workerId) {
        ArrayList<WorkSlotSummary> workSlotDtos = new ArrayList<WorkSlotSummary>();
        for (WorkSlot workSlot : findByWorkerId(workerId)) {
            workSlotDtos.add(new WorkSlotSummary(workSlot));
        }
        return workSlotDtos;
    }

    public Iterable<WorkSlotSummary> findByWorkerIdAndDateDTO(Long workerId, String dateString) {
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ArrayList<WorkSlotSummary> workSlotDtos = new ArrayList<WorkSlotSummary>();
        for (WorkSlot workSlot : findByWorkerId(workerId)) {
            if (workSlot.getDate().equals(date)) {
                workSlotDtos.add(new WorkSlotSummary(workSlot));
            }
        }
        return workSlotDtos;
    }

    public Iterable<WorkSlot> findByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    public WorkSlot getNewest() {
        return repository.getNewest();
    }

    public WorkSlot findById(Long workSlotId) {
        return repository.findById(workSlotId).get();
    }

    public WorkSlotSummary editWorkSlot(Long workSlotId, WorkSlot newWorkSlot) {
        // Get a LIST of 'workSlots' for the parsed in worker ID
        WorkSlot workSlot = repository.findById(workSlotId).get();
        WorkSlotSummary summary = null;
        // If found, update details
        if (workSlot != null) {
            if (workSlotOverlap(newWorkSlot, workSlotId, workSlot.getWorker().getId())) {
                throw new DuplicateKeyException("Workslot overlap on " + newWorkSlot.getDate() + " between "
                        + newWorkSlot.getStartTime() + " and " + newWorkSlot.getEndTime());
            }
            if (bookingSlotOverlap(newWorkSlot, workSlot)) {
                throw new DuplicateKeyException(
                        "New times conflict with this shift's booking slots. Edit booking slots first.");
            }
            if (newWorkSlot.getDate() != null) {
                if (newWorkSlot.getDate().isAfter(LocalDate.now()))
                    workSlot.setDate(newWorkSlot.getDate());
                else
                    throw new DataIntegrityViolationException("Date is in the past");
            }
            if (newWorkSlot.getStartTime() != null) {
                if (newWorkSlot.getStartTime().isBefore(workSlot.getEndTime()))
                    workSlot.setStartTime(newWorkSlot.getStartTime());
                else
                    throw new DataIntegrityViolationException("Start time is after end time");
            }
            if (newWorkSlot.getEndTime() != null) {
                if (newWorkSlot.getEndTime().isAfter(workSlot.getStartTime()))
                    workSlot.setEndTime(newWorkSlot.getEndTime());
                else
                    throw new DataIntegrityViolationException("End Time is before start time");
            }
            repository.save(workSlot);
            summary = new WorkSlotSummary(workSlot);
        }
        return summary;
    }

    public WorkSlotSummary createNewWorkSlot(WorkSlotBlueprint blueprint) {

        Worker worker = workerService.findById(blueprint.getWorkerId()).get();
        Business business = businessService.findById(blueprint.getBusinessId());
        LocalDate date = LocalDate.parse(blueprint.getDate());
        LocalTime startTime = LocalTime.parse(blueprint.getStartTime());
        LocalTime endTime = LocalTime.parse(blueprint.getEndTime());

        if (worker == null) {
            throw new DataRetrievalFailureException("Worker not found");
        }
        if (business == null) {
            throw new DataRetrievalFailureException("Business not found");
        }
        if (startTime.isAfter(endTime)) {
            throw new DataIntegrityViolationException("Start time is after the end time");
        }
        if (date.isBefore(LocalDate.now())      ||
            startTime.isBefore(LocalTime.now()) ||
            endTime.isBefore(LocalTime.now())){
                throw new DataIntegrityViolationException("Can't create slot in the past");
        }

        WorkSlot workslot = new WorkSlot(date, startTime, endTime, worker);

        if (workSlotOverlap(workslot, null, blueprint.getWorkerId())) {
            throw new DuplicateKeyException("Workslot overlap on " + blueprint.getDate() + " between "
                    + blueprint.getStartTime() + " and " + blueprint.getEndTime());
        }

        return new WorkSlotSummary(this.repository.save(workslot));
    }

    public boolean workSlotOverlap(WorkSlot newSlot, Long newSlotId, Long workerId) {
        Iterable<WorkSlotSummary> existingSlots = findByWorkerIdAndDateDTO(workerId,
                newSlot.getDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")));
        for (WorkSlotSummary existingSlot : existingSlots) {
            try {
                if (existingSlot.getStartTime().isBefore(newSlot.getEndTime())
                        && newSlot.getStartTime().isBefore(existingSlot.getEndTime())) {
                    if ((newSlotId == null) || (newSlotId != null && newSlotId != existingSlot.getId())) {
                        return true;
                    }
                }
            } catch (NullPointerException e) {
            }
        }
        return false;
    }

    public boolean bookingSlotOverlap(WorkSlot newSlot, WorkSlot oldSlot) {
        for (BookingSlot existingSlot : oldSlot.getBookingSlots()) {
            try {
                if (existingSlot.getStartTime().isBefore(newSlot.getStartTime())
                        || existingSlot.getEndTime().isAfter(newSlot.getEndTime())) {
                    return true;
                }
            } catch (NullPointerException e) {
            }
        }
        return false;
    }

    public boolean deleteWorkSlot(Long workSlotId) {
        boolean toRet = false;
        WorkSlot workSlot = findById(workSlotId);
        if (workSlot != null) {
            bookingSlotRepository.deleteAll(bookingSlotRepository.findAllByWorkSlotId(workSlotId));
            workSlot.getBookingSlots().clear();
            repository.delete(workSlot);
            toRet = true;
        }
        return toRet;
    }
}
