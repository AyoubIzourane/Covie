package com.example.dashboard.ui.budget_personnelle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dashboard.R;
import com.example.dashboard.databinding.FragmentBudgetPersonnelleBinding;

import java.util.ArrayList;

public class BudgetPersonnelleFragment extends Fragment {

    private BudgetPersonnelleViewModel budgetPersonnelleViewModel;
    private FragmentBudgetPersonnelleBinding binding;

    ArrayList<Product> products;
    ArrayAdapter adapter;
    private static final int APPEL_ACTIV2 = 1;

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



    //New Code here
        


        ListView lv = (ListView)root.findViewById(R.id.listView);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), EditActivity.class);
                intent.putExtra("id", position);
                startActivityForResult(intent,APPEL_ACTIV2);
            }
        });
        Button btn = (Button)root.findViewById(R.id.addButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditActivity.class);
                startActivityForResult(intent,APPEL_ACTIV2);
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