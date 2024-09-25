package com.webtutorial.week2test.entities;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
//import.lombok.*;

@Entity
@Table(name="employeeDB")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
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
