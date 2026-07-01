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

    public boolean updateSalary(String name,double percentage){

        List<Employee> employees=repository.findAll();

        for(Employee e:employees){

            if(e.getName().equalsIgnoreCase(name)){

                double salary=e.getSalary();

                salary=salary+(salary*percentage/100);

                e.setSalary(salary);

                repository.save(e);

                return true;
            }
        }

        return false;
    }

}