package com.example.colocataire_app.BudgetGroupe;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colocataire_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ListActivities extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference databaseReference ;
    List<MainModel> activities;
    Listadapter listAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listactivities);

        FloatingActionButton fb=findViewById(R.id.floatingActionButton);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),NewActivity.class));
            }
        });
        mAuth= FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.listActivities);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        activities =new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Activities");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MainModel data = dataSnapshot.getValue(MainModel.class);
                    activities.add(data);
                }
                listAdapter = new Listadapter(activities);
                recyclerView.setAdapter(listAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }}