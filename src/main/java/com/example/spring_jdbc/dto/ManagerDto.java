package com.example.spring_jdbc.dto;

import com.example.spring_jdbc.model.Project;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ManagerDto {

    @Size(min = 4, max = 20)
    @NotEmpty
    private String name;

    @Min(18)
    @Max(45)
    @NotNull
    private int age;

    @NotNull
    private Project project;

    @NotEmpty
    @Size(min = 2,max = 14)
    private String companyName;
}
