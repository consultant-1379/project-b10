package com.example.cloudnative.repository;

import com.example.cloudnative.models.AreaToStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaToStageRepository extends JpaRepository<AreaToStage, Integer> {
}
