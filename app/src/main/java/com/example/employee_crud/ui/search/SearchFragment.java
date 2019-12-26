package com.example.employee_crud.ui.search;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.employee_crud.R;
import com.example.employee_crud.api.EmployeeAPI;
import com.example.employee_crud.model.Employee;
import com.example.employee_crud.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {


    private SearchViewModel searchViewModel;

    //referencing
    private EditText etEmpNo;
    private TextView tvData;
    private Button btnSearch;


    public static SearchFragment newInstance()
    {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        searchViewModel =
                ViewModelProviders.of(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.search_fragment, container, false);

        etEmpNo = root.findViewById(R.id.etEmpId);
        tvData = root.findViewById(R.id.tvData);
         btnSearch = root.findViewById(R.id.btnSearch);

         btnSearch.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                loadData();
             }
         });

        return root;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        // TODO: Use the ViewModel
    }

    private void loadData()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);

        Call<Employee> listCall = employeeAPI.getEmployeeByID(Integer.parseInt(etEmpNo.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {

                Toast.makeText(getContext(), response.body().toString(), Toast.LENGTH_SHORT).show();

                String content = "";
                content += " ID : " + response.body().getId() + "\n";
                content += " Name : " + response.body().getEmployee_name() + "\n";
                content += " Age : " + response.body().getEmployee_age() + "\n";
                content += " Salary : " + response.body().getEmployee_salary() + "\n";

                tvData.setText(content);


            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
