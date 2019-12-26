package com.example.employee_crud.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.employee_crud.R;
import com.example.employee_crud.api.EmployeeAPI;
import com.example.employee_crud.model.EmployeeCUD;
import com.example.employee_crud.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    private EditText etName, etSalary, etAge;
    private Button btnRegister;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);


          etName = root.findViewById(R.id.etName);
          etSalary = root.findViewById(R.id.etSalary);
          etAge = root.findViewById(R.id.etAge);
          btnRegister = root.findViewById(R.id.btnRegister);

          btnRegister.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  registerEmployee();
              }
          });

        return root;
    }

    private void registerEmployee()
    {

        String name = etName.getText().toString().trim();
        Float salary = Float.parseFloat(etSalary.getText().toString());
        int age = Integer.parseInt(etAge.getText().toString());

        EmployeeCUD employee = new EmployeeCUD(name, salary, age);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);

        Call<Void> voidCall = employeeAPI.registerEmployee(employee);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getContext(), "Successfully employee added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(getContext(), "Errot : "+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}