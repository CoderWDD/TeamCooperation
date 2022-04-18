package com.example.cooperation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserLoginDataViewModel extends ViewModel {
    private MutableLiveData<String> username;
    private MutableLiveData<String> password;

    public MutableLiveData<String> getUsername() {
        if (username == null){
            username = new MutableLiveData<>();
        }
        return username;
    }

    public MutableLiveData<String> getPassword() {
        if (password == null){
            password = new MutableLiveData<>();
        }
        return password;
    }
}
