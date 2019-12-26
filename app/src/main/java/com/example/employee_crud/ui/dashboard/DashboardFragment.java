package com.example.employee_crud.ui.dashboard;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employee_crud.R;
import com.example.employee_crud.adapter.EmployeesAdapter;
import com.example.employee_crud.api.EmployeeAPI;
import com.example.employee_crud.model.Employee;
import com.example.employee_crud.url.URL;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    private EmployeesAdapter employeesAdapter;
    private RecyclerView recyclerView;
   // private List<Employee>employeeList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        //tvOutput = root.findViewById(R.id.tvOutput);
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);

        Call<List<Employee>> listCall = employeeAPI.getEmployees();

        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {

                if (!response.isSuccessful())
                {
                   // tvOutput.setText("code : " + response.code());
                   // Toast.makeText(getActivity(), "hadgf" + response.code(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(),"Error" + response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }

                   LoadDataList(response.body());
            }


            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("Error displayed","onFailure:"+t.getLocalizedMessage());

                // tvOutput.setText("Error" + t.getMessage());
            }
        });

         return root;
    }

    private void LoadDataList(List<Employee>employeeList)
    {
        employeesAdapter = new EmployeesAdapter(employeeList);

        //using a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //set adapter to the recyclerview
        recyclerView.setAdapter(employeesAdapter);
    }


}