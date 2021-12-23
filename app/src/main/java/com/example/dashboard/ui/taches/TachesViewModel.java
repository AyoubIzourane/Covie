package com.example.dashboard.ui.taches;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TachesViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public TachesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is taches fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}