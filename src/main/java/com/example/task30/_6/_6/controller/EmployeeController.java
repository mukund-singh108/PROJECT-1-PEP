package com.example.task30._6._6.controller;

import com.example.task30._6._6.entity.Employee;
import com.example.task30._6._6.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Employee employee) {

        String name = employee.getName();

        if (!name.matches("[A-Za-z]+( [A-Za-z]+){0,2}")) {
            return ResponseEntity.badRequest().body("Invalid Name");
        }

        if (employee.getAge() < 18 || employee.getAge() > 60) {
            return ResponseEntity.badRequest().body("Invalid Age");
        }

        service.save(employee);

        return ResponseEntity.ok(employee);
    }

    @GetMapping("/display")
    public List<Employee> display(){

        return service.display();
    }

    @PutMapping("/salary/{name}/{percentage}")
    public String updateSalary(@PathVariable String name,
                               @PathVariable double percentage){

        if(percentage<1 || percentage>10){
            return "Percentage should be between 1 and 10.";
        }

        boolean status=service.updateSalary(name,percentage);

        if(status)
            return "Salary Updated Successfully";

        return "Employee Not Found";
    }

}