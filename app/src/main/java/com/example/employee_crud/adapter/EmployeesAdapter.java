package com.example.employee_crud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employee_crud.R;
import com.example.employee_crud.model.Employee;

import java.util.List;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.EmployeesViewHolder>{

   //Context context;
    //referencing the lists of employees
    private List<Employee>employeeList;

    public EmployeesAdapter(List<Employee> employeeList)
    {
        this.employeeList = employeeList;
    }


    @NonNull
    @Override
    public EmployeesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_recycler,parent,false);

        return new EmployeesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeesViewHolder holder, int position) {

        Employee employee = employeeList.get(position);
        holder.tvId.setText(Integer.toString(employee.getId()));
        holder.tvName.setText(employee.getEmployee_name());
        holder.tvAge.setText(Integer.toString(employee.getEmployee_age()));
        holder.tvSalary.setText(Float.toString(employee.getEmployee_salary()));
        holder.tvProfileImg.setText(employee.getProfile_image());

    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }


    public class EmployeesViewHolder extends RecyclerView.ViewHolder{

        //referencing the text views
        TextView tvId, tvName, tvAge, tvSalary, tvProfileImg;

        public EmployeesViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvSalary = itemView.findViewById(R.id.tvSalary);
            tvProfileImg = itemView.findViewById(R.id.tvProfileImg);



        }
    }


}
