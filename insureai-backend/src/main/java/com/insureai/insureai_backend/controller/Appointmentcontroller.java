package com.insureai.insureai_backend.controller;

import com.insureai.insureai_backend.dto.Appointmentrequest;
import com.insureai.insureai_backend.model.Appointment;
import com.insureai.insureai_backend.service.Appointmentservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class Appointmentcontroller {

    private final Appointmentservice appointmentService;

    // Customer books appointment
    @PostMapping("/api/customer/appointments/book")
    public ResponseEntity<Appointment> bookAppointment(
            @RequestBody Appointmentrequest request) {
        return ResponseEntity.ok(
                appointmentService.bookAppointment(request));
    }

    // Customer views their appointments
    @GetMapping("/api/customer/appointments/{customerId}")
    public ResponseEntity<List<Appointment>> getCustomerAppointments(
            @PathVariable Long customerId) {
        return ResponseEntity.ok(
                appointmentService.getAppointmentsByCustomer(customerId));
    }

    // Agent views their appointments
    @GetMapping("/api/agent/appointments/{agentId}")
    public ResponseEntity<List<Appointment>> getAgentAppointments(
            @PathVariable Long agentId) {
        return ResponseEntity.ok(
                appointmentService.getAppointmentsByAgent(agentId));
    }

    // Agent confirms or cancels appointment
    @PutMapping("/api/agent/appointments/{id}/status")
    public ResponseEntity<Appointment> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(
                appointmentService.updateStatus(id, status));
    }

    // Customer cancels appointment
    @PutMapping("/api/customer/appointments/{id}/cancel")
    public ResponseEntity<String> cancelAppointment(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                appointmentService.cancelAppointment(id));
    }

    // Admin views all appointments
    @GetMapping("/api/admin/appointments")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(
                appointmentService.getAllAppointments());
    }
}