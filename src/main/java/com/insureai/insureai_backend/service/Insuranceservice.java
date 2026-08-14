package com.insureai.insureai_backend.service;

import com.insureai.insureai_backend.model.Insurance;
import com.insureai.insureai_backend.repository.Insurancerepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Insuranceservice {

    private final Insurancerepository insuranceRepository;

    public List<Insurance> getAllInsurance() {
        return insuranceRepository.findAll();
    }

    public Insurance getInsuranceById(Long id) {
        return insuranceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insurance not found!"));
    }

    public Insurance addInsurance(Insurance insurance) {
        return insuranceRepository.save(insurance);
    }

    public Insurance updateInsurance(Long id, Insurance updated) {
        Insurance insurance = getInsuranceById(id);
        insurance.setPlanId(updated.getPlanId());
        insurance.setDescription(updated.getDescription());
        insurance.setInsuranceType(updated.getInsuranceType());
        insurance.setPremium(updated.getPremium());
        insurance.setAmount(updated.getAmount());
        return insuranceRepository.save(insurance);
    }

    public String deleteInsurance(Long id) {
        insuranceRepository.deleteById(id);
        return "Insurance deleted successfully!";
    }
}