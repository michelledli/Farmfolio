package com.iloveyou.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ExampleEntity {

	@Id 
	@GeneratedValue 
	private Long id;
	private String string;
    private Long number;

	private ExampleEntity() {}

	public ExampleEntity(String string, Long number) {
        this.string = string;
        this.number = number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ExampleEntity entity = (ExampleEntity) o;

		return Objects.equals(id, entity.id) &&
			Objects.equals(string, entity.string) &&
			Objects.equals(number, entity.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, string, number);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "ExampleEntity{" +
			"id=" + id +
			", string='" + string + '\'' +
			", number='" + number + '\'' +
			'}';
	}
}
