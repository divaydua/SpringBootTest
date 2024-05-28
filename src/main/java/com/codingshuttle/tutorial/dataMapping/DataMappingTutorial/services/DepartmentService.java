package com.codingshuttle.tutorial.dataMapping.DataMappingTutorial.services;

import com.codingshuttle.tutorial.dataMapping.DataMappingTutorial.entities.DepartmentEntity;
import com.codingshuttle.tutorial.dataMapping.DataMappingTutorial.entities.EmployeeEntity;
import com.codingshuttle.tutorial.dataMapping.DataMappingTutorial.repositories.DepartmentRepository;
import com.codingshuttle.tutorial.dataMapping.DataMappingTutorial.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public DepartmentEntity createNewDepartment(DepartmentEntity departmentEntity) {
        return departmentRepository.save(departmentEntity);
    }

    public DepartmentEntity getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public DepartmentEntity getDepartmentByManager(Long employeeId) {
//        EmployeeEntity employee = employeeRepository.findById(employeeId).orElse(null);
//        return departmentRepository.findByManager(employee).orElse(null);

        return employeeRepository.findById(employeeId)
                .flatMap(departmentRepository::findByManager)
                .orElse(null);

    }

    public EmployeeEntity getManagerOfDepartment(Long departmentId) {
//        return departmentRepository
//                .findById(departmentId)
//                .map(DepartmentEntity::getManager)
//                .orElse(null);

        return departmentRepository
                .findById(departmentId)
                .flatMap(employeeRepository::findByManagedDepartment)
                .orElse(null);

    }

    public DepartmentEntity assignManagerToDepartment(Long departmentId, Long employeeId) {
        return employeeRepository
                .findById(employeeId)
                .flatMap(employeeEntity -> departmentRepository
                        .findById(departmentId)
                        .map(departmentEntity -> {
                            departmentEntity.setManager(employeeEntity);
                            departmentRepository.save(departmentEntity);
                            return departmentEntity;
                        })).orElse(null);
    }
}
