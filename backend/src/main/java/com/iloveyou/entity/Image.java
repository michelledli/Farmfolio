package com.iloveyou.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Image extends Auditable {
    @Id
    @GeneratedValue
    private Long id;
	private String name;
	@Lob
	private byte[] bytes;
}
