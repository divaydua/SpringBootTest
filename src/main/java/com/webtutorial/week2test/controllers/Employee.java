package com.webtutorial.week2test.controllers;

import com.webtutorial.week2test.DTO.EmployeeDTO;
import com.webtutorial.week2test.entities.EmployeeEntity;
import com.webtutorial.week2test.repository.EmployeeRepository;
import com.webtutorial.week2test.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private final EmployeeRepository employeeRepository;

    public Employee(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path= "/{employeeId}")
    public ResponseEntity<EmployeeDTO > getEmployeeID(@PathVariable Long employeeId){
    Optional<EmployeeDTO> employeeDTO= employeeService.findById(employeeId);
//        if(employeeDTO ==null)
//        {
//            return ResponseEntity.badRequest().build();
//        }
        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1)).
                orElse(ResponseEntity.notFound().build());
        //return new EmployeeDTO(employeeId,"Divay","abc@gmail.com",24, LocalDate.now(),true);
    }

    @GetMapping

    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(@RequestParam (required = false)Integer age,
                               @RequestParam (required= false) String sortBy){
        // Optional<List<EmployeeDTO>> employeeDTO= employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeService.getAllEmployees());
//        return employeeDTO.map(employeeDTOS -> ResponseEntity.ok(employeeDTOS)).
//                orElse(ResponseEntity.notFound().build());
             //   employeeService.getAllEmployees();
       // return "Hi My name is Divay, my age is "+ age + " "+ sortBy;
    }

    @PostMapping(path="/post")

    public ResponseEntity<EmployeeDTO> postEmployee(@RequestBody(required = false) EmployeeEntity employeeEntity){

        return ResponseEntity.ok(employeeService.saveEmployee(employeeEntity));
        // return "This is post request";
    }

    @PostMapping(path="/postNew")

    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO employeeDTO1=employeeService.createNewEmployee(employeeDTO);

        //employeeDTO.setId(100L);
        return new ResponseEntity<>(employeeDTO1, HttpStatus.CREATED);
    }

    @PutMapping(path= "/{employeeId}")

    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDTO employeeDTO){

       return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId,employeeDTO));

    }

    @DeleteMapping(path= "/{employeeId}")

    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long employeeId){
        boolean isDeleted=employeeService.deleteEmployeeById(employeeId);
        if(isDeleted){
            return ResponseEntity.ok(true);
        }
        else return ResponseEntity.notFound().build();
     // return ResponseEntity.ok( employeeService.deleteEmployeeById(employeeId));
    }

    @PatchMapping(path= "/{employeeId}")

    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@PathVariable Long employeeId, @RequestBody Map<String,Object> updates){
        EmployeeDTO employeeDTO=employeeService.updatePartialEmployeeById(employeeId,updates);
        if(employeeDTO==null){
            return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.ok(employeeDTO);
       // return employeeService.updatePartialEmployeeById(employeeId,updates);
    }
}
