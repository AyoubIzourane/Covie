package com.example.dashboard.ui.budget_personnelle;

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

import com.example.dashboard.databinding.FragmentBudgetPersonnelleBinding;

public class BudgetPersonnelleFragment extends Fragment {

    private BudgetPersonnelleViewModel budgetPersonnelleViewModel;
    private FragmentBudgetPersonnelleBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        budgetPersonnelleViewModel =
                new ViewModelProvider(this).get(BudgetPersonnelleViewModel.class);

        binding = FragmentBudgetPersonnelleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textBudgetPersonnelle;
        budgetPersonnelleViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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