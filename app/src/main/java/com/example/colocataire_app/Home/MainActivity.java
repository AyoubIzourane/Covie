package com.example.colocataire_app.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.colocataire_app.Login;
import com.example.colocataire_app.NewAccount;
import com.example.colocataire_app.R;

public class MainActivity extends AppCompatActivity {
    int[] images = {R.drawable.profil2,R.drawable.profil3,R.drawable.profil4,R.drawable.profil5,R.drawable.profil2,R.drawable.profil6};

    String[] names = {"Mounir Aithmed", "Mouad Ahatour", "Anass Ouhammi", "Khalid Boutaib", "Mohammed Karim", "Ali Benabdellah",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }
}