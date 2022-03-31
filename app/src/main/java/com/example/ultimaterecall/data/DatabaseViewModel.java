package com.example.ultimaterecall.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ultimaterecall.data.FakeDatabase;

public class DatabaseViewModel extends ViewModel {
    private final MutableLiveData<FakeDatabase> database = new MutableLiveData<FakeDatabase>();

    public DatabaseViewModel() {
        database.setValue(new FakeDatabase());
    }

    public FakeDatabase getDatabase() {
        return database.getValue();
    }

}