package com.example.colocataire_app.BudgetPersonnelle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.colocataire_app.Home.ListAdapter;
import com.example.colocataire_app.Home.MainActivity;
import com.example.colocataire_app.R;
import com.example.colocataire_app.UserData;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/*
 * @author  : Mouad ahatour
 * @Title   : Application
 */



public class BudgetPersonnelleActivity  extends AppCompatActivity {

    private RecyclerView recyclerView;
    personAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the
    // Firebase Realtime Database
    FloatingActionButton floatingActionButton,floatingActionButton2;  // button ajouter budget
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_personnelle);

        // Create a instance of the database and get
        // its reference
        mbase = FirebaseDatabase.getInstance().getReference("BudgetPerso");

        recyclerView = findViewById(R.id.recycler1);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<com.example.colocataire_app.BudgetPersonnelle.Person> options = new FirebaseRecyclerOptions.Builder<com.example.colocataire_app.BudgetPersonnelle.Person>().setQuery(mbase, com.example.colocataire_app.BudgetPersonnelle.Person.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new com.example.colocataire_app.BudgetPersonnelle.personAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AjouterActivity.class));

            }
        });
        floatingActionButton2=(FloatingActionButton)findViewById(R.id.floatingActionButton3);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("BudgetPerso");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int sum =0;
                for(DataSnapshot ds : snapshot.getChildren()){
                    Map<String, Objects> map = (Map<String, Objects>) ds.getValue();
                    Object budget =map.get("Prix");
                    int pValue = Integer.parseInt(String.valueOf(budget));
                    sum+=pValue;
                    String afficherTotalBudget = String.valueOf(sum);
                    TotalBudgetClass.TotalBudget = afficherTotalBudget;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    //data from database on stopping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}