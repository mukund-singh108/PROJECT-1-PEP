package com.example.task30._6._6.controller;

import com.example.task30._6._6.entity.Employee;
import com.example.task30._6._6.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @GetMapping("/display")
    public List<Employee> display(){

        return service.display();
    }

}