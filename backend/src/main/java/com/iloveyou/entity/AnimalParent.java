//package com.iloveyou.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//public class AnimalParent {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    private String name;
//
//    @OneToMany
//    private List<Animal> children = new ArrayList<>();
//}
