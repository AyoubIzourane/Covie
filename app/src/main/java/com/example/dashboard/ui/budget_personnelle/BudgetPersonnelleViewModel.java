package com.example.dashboard.ui.budget_personnelle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BudgetPersonnelleViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BudgetPersonnelleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is budget personnelle fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}