package com.webtutorial.week2test.DTO;

import java.time.LocalDate;

public class EmployeeDTO {

    private Long id;

    private String name;

    private String email;

    private int age;

    private LocalDate joining;

    private boolean isActive;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getJoining() {
        return joining;
    }

    public void setJoining(LocalDate joining) {
        this.joining = joining;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public EmployeeDTO(Long id, String name, String email, int age, LocalDate joining, boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.joining = joining;
        this.isActive = isActive;
    }

    public EmployeeDTO() {

    }
}
