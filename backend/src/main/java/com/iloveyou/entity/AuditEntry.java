package com.iloveyou.entity;

import java.util.Date;

import org.springframework.data.rest.core.annotation.RestResource;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@RestResource(exported = false)
public class AuditEntry {
	@Id
	@GeneratedValue
	private Long id;
	private String before;
	private String after;
	private String type;
	private Date time;
	private Long accountId;

	public AuditEntry(String before, String after, String type, Date time, Long account) {
		this.before = before;
		this.after = after;
		this.type = type;
		this.time = time;
		this.accountId = account;
	}
}
