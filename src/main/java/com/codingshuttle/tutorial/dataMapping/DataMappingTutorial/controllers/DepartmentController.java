package com.codingshuttle.tutorial.dataMapping.DataMappingTutorial.controllers;

import com.codingshuttle.tutorial.dataMapping.DataMappingTutorial.entities.DepartmentEntity;
import com.codingshuttle.tutorial.dataMapping.DataMappingTutorial.entities.EmployeeEntity;
import com.codingshuttle.tutorial.dataMapping.DataMappingTutorial.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{departmentId}")
    public DepartmentEntity getDepartmentById(@PathVariable Long departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping
    public DepartmentEntity createNewDepartment(@RequestBody DepartmentEntity departmentEntity) {
        return departmentService.createNewDepartment(departmentEntity);
    }

    @GetMapping("/getByManager/{employeeId}")
    public DepartmentEntity getDepartmentByManager(@PathVariable Long employeeId) {
        return departmentService.getDepartmentByManager(employeeId);
    }

    @GetMapping("/{departmentId}/getManagerOfDepartment")
    public EmployeeEntity getManagerOfDepartment(@PathVariable Long departmentId) {
        return departmentService.getManagerOfDepartment(departmentId);
    }

    @PutMapping("/{departmentId}/assignManager/{employeeId}")
    public DepartmentEntity assignManagerToDepartment(@PathVariable Long departmentId, @PathVariable Long employeeId) {
        return departmentService.assignManagerToDepartment(departmentId, employeeId);
    }
}
