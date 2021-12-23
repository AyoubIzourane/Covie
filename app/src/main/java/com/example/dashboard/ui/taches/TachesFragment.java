package com.example.dashboard.ui.taches;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dashboard.databinding.FragmentTachesBinding;

public class TachesFragment extends Fragment {

    private TachesViewModel mViewModel;
    private FragmentTachesBinding binding;

    public static TachesFragment newInstance() {
        return new TachesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel =
                new ViewModelProvider(this).get(TachesViewModel.class);

        binding = FragmentTachesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTaches;
        mViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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