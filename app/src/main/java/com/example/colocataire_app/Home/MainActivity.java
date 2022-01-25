package com.example.colocataire_app.Home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colocataire_app.BudgetPersonnelle.BudgetPersonnelleActivity;

import com.example.colocataire_app.Login;
import com.example.colocataire_app.NewAccount;
import com.example.colocataire_app.R;
import com.example.colocataire_app.UserData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //for username
    private TextView user_name;
    FirebaseUser firebaseUser;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference ;
    UserData userData;
    String username;

    //for a list of users
    List<UserData> userDataList;
    ListAdapter listAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //data base

        user_name = findViewById(R.id.username);

        //To show username from DataBase under that Image
        mAuth= FirebaseAuth.getInstance();
        username = mAuth.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("UserData");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot first_name = snapshot.child(username).child("nom");
                String afficher_fist_name = first_name.getValue().toString().toUpperCase();
                DataSnapshot last_name = snapshot.child(username).child("prenom");
                String afficher_last_name = last_name.getValue().toString().toUpperCase();
                String fullname= afficher_fist_name + " " + afficher_last_name;
                user_name.setText(fullname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        //to show List of "colocataire" from DB


        recyclerView = findViewById(R.id.List);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userDataList =new ArrayList<>();
        List<UserData> modelConfirmedList=new ArrayList<>();

        ImageButton btn_adduser = (ImageButton)findViewById(R.id.btn_ajouter_colocataire);
        btn_adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText adduser = (EditText)findViewById(R.id.edit_text_ajouter_colocataire);
                String colocataire_email= adduser.getText().toString();

                databaseReference = FirebaseDatabase.getInstance().getReference("UserData");
                databaseReference.orderByChild("email").equalTo(colocataire_email).addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getKey();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            UserData data = dataSnapshot.getValue(UserData.class);
                            userDataList.add(data);

                        }

                        listAdapter = new ListAdapter(userDataList);
                        recyclerView.setAdapter(listAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });








        /*

        //show confirmed list

        List<UserData> userDataConfirmedList=new ArrayList<>();
        userDataConfirmedList.addAll(userDataList);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserData");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    UserData data = dataSnapshot.getValue(UserData.class);
                    userDataConfirmedList.add(data);
                }
                listAdapter = new ListAdapter(userDataConfirmedList);
                recyclerView.setAdapter(listAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        */




        /*

        //show all elements in a list

        recyclerView = findViewById(R.id.List);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userDataList =new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserData");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    UserData data = dataSnapshot.getValue(UserData.class);
                    userDataList.add(data);
                }
                listAdapter = new ListAdapter(userDataList);
                recyclerView.setAdapter(listAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        */



//rest of the code include buttons (logout,budget personnelle,budget group)
        //Logout button
        ImageButton btn_logout = (ImageButton) findViewById(R.id.logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        //Budget Personnelle button
        ImageButton btn_budget_personnelle = (ImageButton) findViewById(R.id.budget_personnelle);
        btn_budget_personnelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BudgetPersonnelleActivity.class);
                startActivity(intent);
            }
        });

        /*
        ImageButton btn_budget_group= (ImageButton) findViewById(R.id.budget_group);
        btn_budget_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BudgetGroupActivity.class);
                startActivity(intent);
            }
        });
        */
    }
}