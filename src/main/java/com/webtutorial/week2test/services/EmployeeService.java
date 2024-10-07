package com.webtutorial.week2test.services;

import com.webtutorial.week2test.DTO.EmployeeDTO;
import com.webtutorial.week2test.controllers.Employee;
import com.webtutorial.week2test.entities.EmployeeEntity;
import com.webtutorial.week2test.repository.EmployeeRepository;
import org.aspectj.util.Reflection;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {

        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> findById(Long id) {
       // ModelMapper mapper=new ModelMapper();
        Optional<EmployeeEntity> employeeEntity =employeeRepository.findById(id);
        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1, EmployeeDTO.class));
       // return modelMapper.map(this, EmployeeDTO.class);
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

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {

       EmployeeEntity employeeEntity= modelMapper.map(employeeDTO, EmployeeEntity.class);
       employeeEntity.setId(employeeId);
       EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
       return  modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean isEmployeeExist(Long employeeId) {
        return employeeRepository.existsById(employeeId);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean exists = isEmployeeExist(employeeId);
        if (!exists) {
            return false;}
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String,Object> updates) {

        boolean exists = isEmployeeExist(employeeId);
        if (!exists) {
            return null;
        }
        EmployeeEntity employeeEntity= employeeRepository.findById(employeeId).get();
        updates.forEach((field,value)-> {
            Field tobeupdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
            tobeupdated.setAccessible(true);
            ReflectionUtils.setField(tobeupdated ,employeeEntity, value);
        } );

        return  modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);


    }
}
