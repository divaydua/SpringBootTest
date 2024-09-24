package com.webtutorial.week2test.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="employeeDB")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private int age;

    private LocalDate joining;

    private boolean isActive;


}
