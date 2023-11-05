package com.iloveyou;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iloveyou.entity.AuditEntry;
import com.iloveyou.entity.AbstractEntity;
import com.iloveyou.repository.AuditRepository;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

@Component
public class AuditListener {

	private static AuditRepository auditRepository = null;

	@Autowired
	public void init(AuditRepository auditRepository) {
		AuditListener.auditRepository = auditRepository;
	}

	@PrePersist
	@PreUpdate
	@PreRemove
	public void before(AbstractEntity entity) {
		entity.setSaved(entity.toString());
	}

	@PostPersist
	@PostUpdate
	@PostRemove
	public void after(AbstractEntity entity) {
		String type = entity.getClass().getSimpleName();

		String regex = "\"bytes\":\".*\"";
		final String before = !type.equals("Image") ? entity.getSaved() : entity.getSaved().replaceAll(regex, "");
		String after = !type.equals("Image") ? entity.toString() : entity.toString().replaceAll(regex, "");

		new Thread() {
			@Override
			public void run() {
				auditRepository.save(new AuditEntry(before, after, type, new Date(), entity.getAuditId()));
			}
		}.start();
	}

	@PostLoad
	public void load(AbstractEntity entity) {
	}
}