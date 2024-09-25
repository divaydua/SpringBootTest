package com.webtutorial.week2test.repository;

import com.webtutorial.week2test.controllers.Employee;
import com.webtutorial.week2test.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {


}
