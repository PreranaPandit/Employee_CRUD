package com.example.employee_crud.ui.updatedelete;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.employee_crud.R;
import com.example.employee_crud.api.EmployeeAPI;
import com.example.employee_crud.model.Employee;
import com.example.employee_crud.model.EmployeeCUD;
import com.example.employee_crud.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateDeleteFragment extends Fragment {

    private UpdateDeleteViewModel updateDeleteViewModel;
    private Button btnSearch, btnUpdate, btnDelete;
    private EditText etEmpNo;
    private EditText etEmpName, etAge, etSalary;

    Retrofit retrofit;
    EmployeeAPI employeeAPI;

    public static UpdateDeleteFragment newInstance() {
        return new UpdateDeleteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        updateDeleteViewModel =
                ViewModelProviders.of(this).get(UpdateDeleteViewModel.class);
        final View root = inflater.inflate(R.layout.update_delete_fragment, container, false);

        btnSearch = root.findViewById(R.id.btnSearch);
        btnUpdate = root.findViewById(R.id.btnUpdate);
        btnDelete = root.findViewById(R.id.btnDelete);

        etEmpNo = root.findViewById(R.id.etEmpNo);
        etEmpName = root.findViewById(R.id.etEmployeeName);
        etAge = root.findViewById(R.id.etEmployeeAge);
        etSalary = root.findViewById(R.id.etEmpSalary);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployee();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee();
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateDeleteViewModel = ViewModelProviders.of(this).get(UpdateDeleteViewModel.class);
        // TODO: Use the ViewModel
    }

    private void CreateInstance()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        employeeAPI = retrofit.create(EmployeeAPI.class);

    }

    private void loadData()
    {
        CreateInstance();
        Call<Employee> listCall = employeeAPI.getEmployeeByID(Integer.parseInt(etEmpNo.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {

                etEmpName.setText(response.body().getEmployee_name());
                etSalary.setText(Float.toString(response.body().getEmployee_salary()));
                etAge.setText(Integer.toString(response.body().getEmployee_age()));

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {

                Toast.makeText(getContext(), "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateEmployee()
    {
        CreateInstance();
        EmployeeCUD employee = new EmployeeCUD(
          etEmpName.getText().toString(),
          Float.parseFloat(etSalary.getText().toString()),
                Integer.parseInt(etAge.getText().toString())
        );

        Call<Void> voidCall = employeeAPI.updateEmployee(Integer.parseInt(etEmpNo.getText().toString()),employee);
        
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getContext(), "Updated successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteEmployee()
    {
        CreateInstance();
        Call<Void> voidCall = employeeAPI.deleteEmployee(Integer.parseInt(etEmpNo.getText().toString()));

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getContext(), "Deleted successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
