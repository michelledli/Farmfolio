package com.iloveyou.entity;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AnimalEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Date birthDate;
    private int weight;
    private String tag;
    private String breed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnimalEntity entity = (AnimalEntity) o;

        return Objects.equals(id, entity.id) &&
                Objects.equals(name, entity.name) &&
                Objects.equals(birthDate, entity.birthDate) &&
                Objects.equals(weight, entity.weight) &&
                Objects.equals(tag, entity.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate, weight, tag);
    }

    @Override
    public String toString() {
        return "AnimalEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate='" + birthDate + '\'' +
                ", weight='" + weight + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
