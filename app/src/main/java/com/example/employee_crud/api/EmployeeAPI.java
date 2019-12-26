package com.example.employee_crud.api;

import com.example.employee_crud.model.Employee;
import com.example.employee_crud.model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeAPI {

    //get all the employees lists
    @GET("employees")
    Call<List<Employee>> getEmployees();

    //get employee based upon employee id
    @GET("employee/{empID}")
    Call<Employee> getEmployeeByID(@Path("empID") int empId);

    //post method to create employee
    @POST("create")
    Call<Void> registerEmployee(@Body EmployeeCUD emp);

    //update employee on the basis of emp id
    @PUT("update/{empID}")
    Call<Void> updateEmployee(@Path("empID") int empId, @Body EmployeeCUD emp);

    @DELETE("delete/{empID}")
    Call<Void> deleteEmployee(@Path("empID") int empId);


}
