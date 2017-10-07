package com.programmers.apply.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.programmers.apply.entities.Position;

@Repository
@Transactional
public interface PositionRepository extends JpaRepository<Position, Long> {

}