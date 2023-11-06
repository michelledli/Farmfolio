package com.iloveyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iloveyou.entity.AuditEntry;
import com.iloveyou.repository.AuditRepository;

@Controller
@RequestMapping("/audit")
public class AuditController {
	@Autowired
	AuditRepository auditRepository;

	@GetMapping
    public ResponseEntity<?> getAll() {
        Iterable<AuditEntry> a = auditRepository.findAll();
		return ResponseEntity.ok(a);
    }
}
