package com.example.dashboard.ui.budget_group;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BudgetGroupViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BudgetGroupViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is budget group fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}