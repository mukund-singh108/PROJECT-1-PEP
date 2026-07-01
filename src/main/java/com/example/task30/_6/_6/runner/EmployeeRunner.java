package com.example.task30._6._6.runner;

import com.example.task30._6._6.entity.Employee;
import com.example.task30._6._6.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class EmployeeRunner implements CommandLineRunner {

    @Autowired
    private EmployeeService service;

    @Override
    public void run(String... args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n========== EMPLOYEE MENU ==========");
            System.out.println("1. Create Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Update Salary");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    String ch;

                    do {

                        String name;

                        while (true) {

                            System.out.print("Enter Name: ");
                            name = sc.nextLine().trim();

                            // Only alphabets and maximum 2 spaces (3 words)
                            if (name.matches("[A-Za-z]+( [A-Za-z]+){0,2}")) {
                                break;
                            }

                            System.out.println("Invalid Name!");
                            System.out.println("Only alphabets allowed and maximum 2 spaces.");
                        }

                        int age;

                        while (true) {

                            System.out.print("Enter Age (18-60): ");
                            age = sc.nextInt();
                            sc.nextLine();

                            if (age >= 18 && age <= 60) {
                                break;
                            }

                            System.out.println("Age must be between 18 and 60.");
                        }

                        String designation;
                        double salary;

                        while (true) {

                            System.out.print("Enter Designation (Programmer/Manager/Tester): ");
                            designation = sc.nextLine();

                            if (designation.equalsIgnoreCase("Programmer")) {

                                salary = 25000;
                                break;

                            } else if (designation.equalsIgnoreCase("Manager")) {

                                salary = 300000;
                                break;

                            } else if (designation.equalsIgnoreCase("Tester")) {

                                salary = 20000;
                                break;

                            } else {

                                System.out.println("Invalid Designation!");
                            }
                        }

                        Employee employee = new Employee(name, age, designation, salary);

                        service.save(employee);

                        System.out.println("Employee Saved Successfully.");

                        System.out.print("Add Another Employee? (y/n): ");
                        ch = sc.nextLine();

                    } while (ch.equalsIgnoreCase("y"));

                    break;

                case 2:

                    System.out.println("\n========== EMPLOYEE LIST ==========");

                    if (service.display().isEmpty()) {

                        System.out.println("No Employees Found.");

                    } else {

                        service.display().forEach(System.out::println);

                    }

                    break;

                case 3:

                    String option;

                    do {

                        System.out.print("Enter Employee Name: ");
                        String empName = sc.nextLine();

                        double percentage;

                        while (true) {

                            System.out.print("Enter Salary Increase Percentage (1-10): ");
                            percentage = sc.nextDouble();
                            sc.nextLine();

                            if (percentage >= 1 && percentage <= 10) {
                                break;
                            }

                            System.out.println("Percentage must be between 1 and 10.");
                        }

                        boolean status = service.updateSalary(empName, percentage);

                        if (status) {

                            System.out.println("Salary Updated Successfully.");

                        } else {

                            System.out.println("Employee Not Found.");
                        }

                        System.out.print("Update Another Employee? (y/n): ");
                        option = sc.nextLine();

                    } while (option.equalsIgnoreCase("y"));

                    break;

                case 4:

                    System.out.println("Thank You!");
                    sc.close();
                    System.exit(0);

                default:

                    System.out.println("Invalid Choice!");

            }
        }
    }
}