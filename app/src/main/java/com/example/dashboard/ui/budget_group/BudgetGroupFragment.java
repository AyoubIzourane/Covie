package com.example.dashboard.ui.budget_group;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dashboard.databinding.FragmentBudgetGroupBinding;

public class BudgetGroupFragment extends Fragment {

    private BudgetGroupViewModel budgetGroupViewModel;
    private FragmentBudgetGroupBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        budgetGroupViewModel =
                new ViewModelProvider(this).get(BudgetGroupViewModel.class);

        binding = FragmentBudgetGroupBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textBudgetGroup;
        budgetGroupViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}