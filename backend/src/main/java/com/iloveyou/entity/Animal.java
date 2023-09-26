package com.iloveyou.entity;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //generates setters and getters upon build
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Animal {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String sex;
    private Date dob;
    private int weight;
    private String tag;
    private String breed;
    private String notes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal entity = (Animal) o;

        return Objects.equals(id, entity.id) &&
                Objects.equals(name, entity.name) &&
                Objects.equals(dob, entity.dob) &&
                Objects.equals(weight, entity.weight) &&
                Objects.equals(tag, entity.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dob, weight, tag);
    }

    @Override
    public String toString() {
        return "AnimalEntity{" +
                "id='" + id +
                ", name='" + name + '\'' +
                ", birthdate='" + dob + '\'' +
                ", weight='" + weight + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    } 

}
