package com.iloveyou.entity;

import java.util.Date;
import java.util.Objects;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Animal extends AbstractEntity {
    private String name;
    private String sex;
    private Date dob;
    private int weight;
    private String tag;
    private String breed;
    private String notes;
    private Long imageId;

    //parents
    @ManyToOne
    @JoinColumn(name = "father_id")
    private Animal father;

    @ManyToOne
    @JoinColumn(name = "mother_id")
    private Animal mother;

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
        return super.toString();
    }
}
