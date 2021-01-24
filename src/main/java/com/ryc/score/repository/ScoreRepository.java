package com.ryc.score.repository;

import com.ryc.score.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScoreRepository extends JpaRepository<ScoreEntity, UUID> {
}
