package com.example.employee_crud.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.employee_crud.R;
import com.example.employee_crud.ui.dashboard.DashboardFragment;
import com.example.employee_crud.ui.notifications.NotificationsFragment;
import com.example.employee_crud.ui.search.SearchFragment;
import com.example.employee_crud.ui.updatedelete.UpdateDeleteFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private Button btnSeeAll;
    private Button btnAdd;
    private Button btnSearch;
    private Button btnUpdateDelete;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        btnSeeAll = root.findViewById(R.id.btnShowAll);
        btnAdd = root.findViewById(R.id.btnRegister);
        btnSearch = root.findViewById(R.id.btnSearch);
        btnUpdateDelete = root.findViewById(R.id.btnUpdateDelete);


        btnSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                DashboardFragment nextFrag= new DashboardFragment();
//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.nav_host_fragment, nextFrag, "findThisFragment")
//                        .addToBackStack(null)
//                        .commit();

                Fragment someFragment = new DashboardFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment searchFragment = new SearchFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, searchFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment addFragment = new NotificationsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, addFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });


        btnUpdateDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment updateFragment = new UpdateDeleteFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, updateFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        return root;
    }
}