package com.example.dashboard.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dashboard.R;
import com.example.dashboard.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {


    int[] images = {R.drawable.profil2,R.drawable.profil3,R.drawable.profil4,R.drawable.profil5,R.drawable.profil2,R.drawable.profil6};

    String[] names = {"Mounir Aithmed", "Mouad Ahatour", "Anass Ouhammi", "Khalid Boutaib", "Mohammed Karim", "Ali Benabdellah",};

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.username;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        ListView lView = (ListView) root.findViewById(R.id.List);

        ListAdapter lAdapter = new ListAdapter(getActivity(), names, images);

        lView.setAdapter(lAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}