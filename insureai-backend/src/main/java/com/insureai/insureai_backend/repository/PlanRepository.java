package com.insureai.insureai_backend.repository;

import com.insureai.insureai_backend.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}