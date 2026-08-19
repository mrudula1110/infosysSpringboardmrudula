package com.insureai.insureai_backend.repository;

import com.insureai.insureai_backend.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Insurancerepository extends JpaRepository<Insurance, Long> {
}