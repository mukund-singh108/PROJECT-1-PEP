package com.example.task30._6._6.service;

import com.example.task30._6._6.entity.Employee;

import com.example.task30._6._6.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    public void save(Employee employee){
        repository.save(employee);
    }

    public List<Employee> display(){
        return repository.findAll();
    }

    public void raiseSalary(){

        List<Employee> employees = repository.findAll();

        for(Employee e : employees){

            e.setSalary(e.getSalary()+5000);

            repository.save(e);
        }
    }

}