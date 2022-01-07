package com.example.colocataire_app.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.colocataire_app.BudgetPersonnelle.BudgetPersonnelleActivity;
import com.example.colocataire_app.Login;
import com.example.colocataire_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private TextView user_name;
    FirebaseUser firebaseUser;
    FirebaseAuth mAuth;


    int[] images = {R.drawable.profil2,R.drawable.profil3,R.drawable.profil4,R.drawable.profil5,R.drawable.profil2,R.drawable.profil6};

    String[] names = {"Mounir Aithmed", "Mouad Ahatour", "Anass Ouhammi", "Khalid Boutaib", "Mohammed Karim", "Ali Benabdellah",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //data base
        user_name = findViewById(R.id.username);
        //        Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        user_name.setText(firebaseUser.getDisplayName());


//rest of the code
        ListView lView = (ListView) findViewById(R.id.List);
        ListAdapter lAdapter = new ListAdapter(this, names, images);
        lView.setAdapter(lAdapter);

        ImageButton btn_logout = (ImageButton) findViewById(R.id.logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        ImageButton btn_budget_personnelle = (ImageButton) findViewById(R.id.budget_personnelle);
        btn_budget_personnelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BudgetPersonnelleActivity.class);
                startActivity(intent);
            }
        });

    }
}