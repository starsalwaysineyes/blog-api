package com.jt.repository;

import com.jt.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Integer> {
}