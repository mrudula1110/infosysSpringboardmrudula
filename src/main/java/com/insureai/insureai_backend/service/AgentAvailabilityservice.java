package com.insureai.insureai_backend.service;

import com.insureai.insureai_backend.dto.Availabilityrequest;
import com.insureai.insureai_backend.model.Agentavailability;
import com.insureai.insureai_backend.repository.Agentavailabilityrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentAvailabilityservice {

    private final Agentavailabilityrepository availabilityRepository;

    // Agent sets availability
    public Agentavailability setAvailability(Availabilityrequest request) {
        Agentavailability availability = new Agentavailability();
        availability.setAgentId(request.getAgentId());
        availability.setDate(request.getDate());
        availability.setTimeSlot(request.getTimeSlot());
        availability.setAvailable(request.isAvailable());
        return availabilityRepository.save(availability);
    }

    // Get all availability for an agent
    public List<Agentavailability> getAgentAvailability(Long agentId) {
        return availabilityRepository.findByAgentId(agentId);
    }

    // Get availability for agent on specific date
    public List<Agentavailability> getAvailabilityByDate(
            Long agentId, String date) {
        return availabilityRepository.findByAgentIdAndDate(agentId, date);
    }

    // Get only available slots for an agent
    public List<Agentavailability> getAvailableSlots(Long agentId) {
        return availabilityRepository
                .findByAgentIdAndIsAvailable(agentId, true);
    }

    // Update availability slot
    public Agentavailability updateAvailability(
            Long id, boolean isAvailable) {
        Agentavailability availability = availabilityRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Availability slot not found!"));
        availability.setAvailable(isAvailable);
        return availabilityRepository.save(availability);
    }

    // Delete availability slot
    public String deleteAvailability(Long id) {
        availabilityRepository.deleteById(id);
        return "Availability slot deleted!";
    }
}