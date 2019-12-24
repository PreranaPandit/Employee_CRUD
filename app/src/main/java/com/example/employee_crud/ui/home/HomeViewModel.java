package com.example.employee_crud.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("EMPLOYEE MANAGEMENT SYSTEM");
    }

    public LiveData<String> getText() {
        return mText;
    }
}