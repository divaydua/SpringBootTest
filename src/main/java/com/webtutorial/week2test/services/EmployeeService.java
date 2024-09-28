package com.webtutorial.week2test.services;

import com.webtutorial.week2test.DTO.EmployeeDTO;
import com.webtutorial.week2test.controllers.Employee;
import com.webtutorial.week2test.entities.EmployeeEntity;
import com.webtutorial.week2test.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {

        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO findById(Long id) {
       // ModelMapper mapper=new ModelMapper();
        return modelMapper.map(this, EmployeeDTO.class);
       //return employeeRepository.findById(id).orElse(null);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
       return employeeEntities.stream().
                map(EmployeeEntity->modelMapper.map(EmployeeEntity, EmployeeDTO.class)).
                collect(Collectors.toList());
//       return employeeEntities.forEach(e -> modelMapper.map(e, EmployeeDTO.class));

        // modelMapper.map(employeeRepository.findAll(), List.class);

    }

    public EmployeeDTO saveEmployee(EmployeeEntity employeeEntity) {
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
      return  modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
       // return employeeRepository.save(employeeEntity);
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
       return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }
}
