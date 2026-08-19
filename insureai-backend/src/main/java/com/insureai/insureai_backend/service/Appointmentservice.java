package com.insureai.insureai_backend.service;

import com.insureai.insureai_backend.dto.Appointmentrequest;
import com.insureai.insureai_backend.model.Appointment;
import com.insureai.insureai_backend.repository.Appointmentrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Appointmentservice {

    private final Appointmentrepository appointmentRepository;

    // Book appointment with conflict check
    public Appointment bookAppointment(Appointmentrequest request) {
        boolean isConflict = appointmentRepository // Check if slot already booked
                .existsByAgentIdAndDateAndTimeSlot(
                        request.getAgentId(),
                        request.getDate(),
                        request.getTimeSlot());

        if (isConflict) {
            throw new RuntimeException(
                    "This time slot is already booked for this agent!");
        }

        Appointment appointment = new Appointment();
        appointment.setCustomerId(request.getCustomerId());
        appointment.setAgentId(request.getAgentId());
        appointment.setDate(request.getDate());
        appointment.setTimeSlot(request.getTimeSlot());
        appointment.setReason(request.getReason());
        appointment.setStatus("PENDING");

        return appointmentRepository.save(appointment);
    }

    // Get appointments by customer
    public List<Appointment> getAppointmentsByCustomer(Long customerId) {
        return appointmentRepository.findByCustomerId(customerId);
    }

    // Get appointments by agent
    public List<Appointment> getAppointmentsByAgent(Long agentId) {
        return appointmentRepository.findByAgentId(agentId);
    }

    // Get all appointments (admin)
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Update appointment status
    public Appointment updateStatus(Long id, String status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found!"));
        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }

    // Cancel appointment
    public String cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found!"));
        appointment.setStatus("CANCELLED");
        appointmentRepository.save(appointment);
        return "Appointment cancelled successfully!";
    }
}