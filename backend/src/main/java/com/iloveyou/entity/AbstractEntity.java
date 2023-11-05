package com.iloveyou.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import com.iloveyou.AuditListener;
import com.iloveyou.entity.AbstractEntity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditListener.class)
public abstract class AbstractEntity {
	@Id
	@GeneratedValue
	protected Long id;

	@Transient
	@JsonIgnore
	private transient String saved;

	@Transient
	private transient Long auditId;

	@JsonIgnore
	public Long getAuditId() {
		return this.auditId;
	}

	@JsonSetter
	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}

	@JsonIgnore
	public void setSaved(String saved) {
		this.saved = saved;
	}

	@JsonIgnore
	public String getSaved() {
		return this.saved;
	}

	@Override
	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return "{}";
		}
	}
}
