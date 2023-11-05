package com.iloveyou.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Image extends AbstractEntity {
	private String name;
	@Lob
	private byte[] bytes;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Image entity = (Image) o;

		return Objects.equals(id, entity.id) &&
				Objects.equals(name, entity.name) &&
				Objects.equals(bytes, entity.bytes);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, bytes);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
