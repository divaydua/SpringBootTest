package com.codingshuttle.tutorial.dataMapping.DataMappingTutorial.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToOne
    @JoinColumn(name = "manager_ref", referencedColumnName = "id")
    private EmployeeEntity manager;

    @OneToMany
    @JoinTable(
            name = "worker_department",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name="employee_id")
    )
    @JsonIgnore
    private Set<EmployeeEntity> workers;

    @ManyToMany
    @JoinTable(
            name = "freelancer_department",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name="employee_id")
    )
    private Set<EmployeeEntity> freelancers;
}
