package com.iloveyou.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.iloveyou.entity.AuditEntry;

public interface AuditRepository extends JpaRepository<AuditEntry, Void> {
}

