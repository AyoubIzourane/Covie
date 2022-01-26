package com.example.colocataire_app.BudgetPersonnelle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.colocataire_app.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
/*
 * @author  : Mouad ahatour
 * @Title   : Application
 */



public class BudgetPersonnelleActivity  extends AppCompatActivity {

    private RecyclerView recyclerView;
    com.example.colocataire_app.BudgetPersonnelle.personAdapter
            adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the
    // Firebase Realtime Database

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
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}