package com.example.employee_crud.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.employee_crud.R;
import com.example.employee_crud.api.EmployeeAPI;
import com.example.employee_crud.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private TextView tvOutput;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        tvOutput = root.findViewById(R.id.tvOutput);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dummy.restapiexample.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);

        Call<List<Employee>> listCall = employeeAPI.getEmployee();

        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {

                if (!response.isSuccessful())
                {
                    tvOutput.setText("code : " + response.code());
                }

                List<Employee> employeeList = response.body();
                for(Employee employee : employeeList)
                {
                    String data = "";
                    data += "ID : " + employee.getId() + "\n";
                    data += "employee_name : " + employee.getEmployee_name() + "\n";
                    data += "employee_salary : " + employee.getEmployee_salary() + "\n";
                    data += "employee_age : " + employee.getEmployee_age() + "\n";
                    data += "profile_image : " + employee.getProfile_image() + "\n";
                    data += "---------------------------------------" + "\n";
                    tvOutput.append(data);
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

                tvOutput.setText("Error" + t.getMessage());
            }
        });

        return root;
    }
}