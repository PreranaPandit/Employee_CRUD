package com.example.employee_crud.api;

import com.example.employee_crud.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeAPI {

    @GET("employees")
    Call<List<Employee>> getEmployee();


}
