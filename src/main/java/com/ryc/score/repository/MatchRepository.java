package com.ryc.score.repository;

import com.ryc.score.entity.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MatchRepository extends JpaRepository<MatchEntity, UUID> {
}
