package com.example.employee_crud.api;

import com.example.employee_crud.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmployeeAPI {

    //get all the employees lists
    @GET("employees")
    Call<List<Employee>> getEmployees();

    //get employee based upon employee id
    @GET("employee/{empID}")
    Call<Employee> getEmployeeByID(@Path("empID") int empId);


}
