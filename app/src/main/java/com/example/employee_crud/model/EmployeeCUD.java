package com.example.employee_crud.model;

public class EmployeeCUD {

    private int id;
    private String name;
    private float salary;
    private int age;
    private String profile_image;

    public EmployeeCUD(String name, float salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }
}
