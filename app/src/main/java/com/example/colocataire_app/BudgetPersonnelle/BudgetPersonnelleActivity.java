package com.example.colocataire_app.BudgetPersonnelle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.colocataire_app.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
/*
 * @author  : Mouad ahatour
 * @Title   : Application
 */



public class BudgetPersonnelleActivity extends AppCompatActivity {
     RecyclerView recyclerView;
     MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_personnelle);
     recyclerView=(RecyclerView)findViewById(R.id.rv);
     recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("budget"), MainModel.class)
                        .build();
        mainAdapter= new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.startListening();
    }

}

