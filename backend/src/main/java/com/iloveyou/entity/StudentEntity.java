package com.iloveyou.entity;


import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data //generates setters and getters upon build
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StudentEntity extends UserEntity {
    private String studentId;

}