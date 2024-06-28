package com.example.spring_jdbc.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
    private Integer id;
    private String name;
    private int age;
    private Project project;
    private String companyName;

    public Manager(String name, int age, Project project, String companyName) {
        this.name = name;
        this.age = age;
        this.project = project;
        this.companyName = companyName;
    }
}
