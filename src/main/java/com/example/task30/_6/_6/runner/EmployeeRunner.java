package com.example.task30._6._6.runner;

import com.example.task30._6._6.entity.Employee;
import com.example.task30._6._6.service.EmployeeService;
import com.example.task30._6._6.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class EmployeeRunner implements CommandLineRunner {

    @Autowired
    EmployeeService service;

    @Override
    public void run(String... args) {

        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.println("\n1.Create");
            System.out.println("2.Display");
            System.out.println("3.Raise Salary");
            System.out.println("4.Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:

                    String ch;

                    do{

                        System.out.println("Enter Name");
                        String name=sc.nextLine();

                        System.out.println("Enter Age");
                        int age=sc.nextInt();
                        sc.nextLine();

                        System.out.println("Enter Designation");
                        String des=sc.nextLine();

                        double salary=0;

                        if(des.equalsIgnoreCase("Programmer"))
                            salary=25000;

                        else if(des.equalsIgnoreCase("Manager"))
                            salary=300000;

                        else if(des.equalsIgnoreCase("Tester"))
                            salary=20000;

                        Employee employee=new Employee(name,age,des,salary);

                        service.save(employee);

                        System.out.println("Continue? (y/n)");

                        ch=sc.nextLine();

                    }while(ch.equalsIgnoreCase("y"));

                    break;

                case 2:

                    service.display().forEach(System.out::println);

                    break;

                case 3:

                    service.raiseSalary();

                    System.out.println("Salary Raised");

                    break;

                case 4:

                    System.exit(0);

            }

        }

    }
}