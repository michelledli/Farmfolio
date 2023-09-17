package com.iloveyou.entity;


import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data //generates setters and getters upon build
@Entity
public class StaffEntity extends UserEntity{
    private String phone;
}
