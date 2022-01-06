package com.example.colocataire_app.BudgetPersonnelle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;



public class BudgetPersonnelleActivity extends AppCompatActivity {
    protected ArrayList<Product> products;
    private ArrayAdapter adapter;

    private static final int APPEL_ACTIV2 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_personnelle);


        MyApplication context = (MyApplication) this.getApplicationContext();
        products = context.getProducts();
        adapter = new ProductAdapter(this,products);



        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BudgetPersonnelleActivity.this, EditActivity.class);
                intent.putExtra("id", position);
                startActivityForResult(intent,APPEL_ACTIV2);
            }
        });
        Button btn = (Button)findViewById(R.id.addButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BudgetPersonnelleActivity.this, EditActivity.class);
                startActivityForResult(intent,APPEL_ACTIV2);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            adapter.notifyDataSetChanged();
        } else if(resultCode == Activity.RESULT_CANCELED) {}
        else {Toast.makeText(this, "Something's wrong !", Toast.LENGTH_SHORT).show(); }
    }
}