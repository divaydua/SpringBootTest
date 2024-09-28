package com.webtutorial.week2test.controllers;

import com.webtutorial.week2test.DTO.EmployeeDTO;
import com.webtutorial.week2test.entities.EmployeeEntity;
import com.webtutorial.week2test.repository.EmployeeRepository;
import com.webtutorial.week2test.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//import static jdk.jpackage.internal.MacDmgBundler.required;

@RequestMapping (path= "/employee")

@RestController
public class Employee {

//    @GetMapping(path = "/employee")
//
//    public String getsecretmessage() {
//        return "secret message";
//    }

    private final EmployeeService employeeService;

    public Employee(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path= "/{employeeId}")
    public EmployeeDTO getEmployeeID(@PathVariable Long employeeId){

        return employeeService.findById(employeeId);

        //return new EmployeeDTO(employeeId,"Divay","abc@gmail.com",24, LocalDate.now(),true);
    }

    @GetMapping

    public List<EmployeeDTO> getAllEmployee(@RequestParam (required = false)Integer age,
                               @RequestParam (required= false) String sortBy){

        return employeeService.getAllEmployees();
       // return "Hi My name is Divay, my age is "+ age + " "+ sortBy;
    }

    @PostMapping(path="/post")

    public EmployeeDTO postEmployee(@RequestBody(required = false) EmployeeEntity employeeEntity){

        return employeeService.saveEmployee(employeeEntity);
        // return "This is post request";
    }

    @PostMapping(path="/postNew")

    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeDTO.setId(100L);
        return employeeDTO;
    }


}
