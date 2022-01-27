package com.example.colocataire_app.BudgetPersonnelle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.colocataire_app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AjouterActivity extends AppCompatActivity {
    EditText Home,Date,Prix;
    Button btnAdd,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        Home=(EditText)findViewById(R.id.txtName);
        Date=(EditText)findViewById(R.id.txtDate);
        Prix=(EditText)findViewById(R.id.txtPrix);

        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnBack=(Button)findViewById(R.id.btnBack);
       btnAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               insertData();
               clearAll();
           }
       });
       btnBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
    }
    private void insertData(){
        Map<String,Object> map=new HashMap<>();

        map.put("Home",Home.getText().toString());
        map.put("Date",Date.getText().toString());
        map.put("Prix",Prix.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("BudgetPerso").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AjouterActivity.this, "donnée inserer avec succes", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AjouterActivity.this, "probleme d'insertion", Toast.LENGTH_SHORT).show();
                    }
                });
    }
     private void clearAll()
     {
       Home.setText("");
       Date.setText("");
       Prix.setText("");
     }
}