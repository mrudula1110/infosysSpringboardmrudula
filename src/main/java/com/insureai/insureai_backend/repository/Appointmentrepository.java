package com.insureai.insureai_backend.repository;

import com.insureai.insureai_backend.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface Appointmentrepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByCustomerId(Long customerId);

    List<Appointment> findByAgentId(Long agentId);

    boolean existsByAgentIdAndDateAndTimeSlot(Long agentId, String date, String timeSlot);
}