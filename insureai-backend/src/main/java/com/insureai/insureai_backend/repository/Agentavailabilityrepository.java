package com.insureai.insureai_backend.repository;

import com.insureai.insureai_backend.model.Agentavailability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface Agentavailabilityrepository
        extends JpaRepository<Agentavailability, Long> {

    List<Agentavailability> findByAgentId(Long agentId);

    List<Agentavailability> findByAgentIdAndDate(Long agentId, String date);

    List<Agentavailability> findByAgentIdAndIsAvailable(
            Long agentId, boolean isAvailable);
}