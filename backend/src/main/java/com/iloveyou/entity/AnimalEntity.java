package com.iloveyou.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

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

    private AnimalEntity() {}

    public AnimalEntity(String name, Date birthDate, int weight, String tag) {
        this.name = name;
        this.birthDate = birthDate;
        this.weight = weight;
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnimalEntity entity = (AnimalEntity) o;

        return Objects.equals(id, entity.id) &&
                Objects.equals(name, entity.name) &&
                Objects.equals(birthDate, entity.birthDate) &&
                Objects.equals(weight, entity.weight) &&
                Objects.equals(tag, entity.tag) &&;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate, weight, tag);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    public int getWeight() { return weight; }

    public void setWeight(int weight) { this.weight = weight; }

    public String getTag() { return tag; }

    public void setTag(String tag) { this.tag = tag; }

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
