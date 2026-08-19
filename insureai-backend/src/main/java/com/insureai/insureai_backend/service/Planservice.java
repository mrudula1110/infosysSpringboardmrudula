package com.insureai.insureai_backend.service;

import com.insureai.insureai_backend.model.Plan;
import com.insureai.insureai_backend.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Planservice {

    private final PlanRepository planRepository;

    // Get all plans
    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }

    // Get plan by id
    public Plan getPlanById(Long id) {
        return planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found!"));
    }

    // Add plan
    public Plan addPlan(Plan plan) {
        return planRepository.save(plan);
    }

    // Update plan
    public Plan updatePlan(Long id, Plan updatedPlan) {
        Plan plan = getPlanById(id);
        plan.setPlanName(updatedPlan.getPlanName());
        plan.setPlanType(updatedPlan.getPlanType());
        plan.setPlanDescription(updatedPlan.getPlanDescription());
        return planRepository.save(plan);
    }

    // Delete plan
    public String deletePlan(Long id) {
        planRepository.deleteById(id);
        return "Plan deleted successfully!";
    }
}