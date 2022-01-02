package com.example.dashboard.ui.budget_personnelle;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dashboard.R;

import java.util.ArrayList;

/*
 * @author  : Mouad ahatour

 */

public class  EditActivity extends AppCompatActivity {
    private MyApplication app;
    EditText pName, pPrice;
    private int identifiant = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        pName = (EditText)findViewById(R.id.nameTF);
        pPrice = (EditText)findViewById(R.id.priceTF);

        Intent intent = getIntent();
        identifiant = intent.getIntExtra("id",-1);
        if(identifiant < 0) {
            setTitle("new product");
        }
        else {
            setTitle("edit your product");
        }

        getSelectedRow();

        // set new data to the selected item !
        Button btnSubmit = (Button)findViewById(R.id.submitBtn);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onValider();
                setResult(RESULT_OK);
                finish();
            }
        });

        // Delete Selected Item from ListView !
        Button btnDelete = (Button)findViewById(R.id.deleteBtn);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem();
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    public void getSelectedRow () {
        app = (MyApplication)getApplicationContext();
        ArrayList<Product> products = app.getProducts();

        if(identifiant < 0) {
            pName.setText(" ");
            pPrice.setText("0");
        } else {
            Product product = products.get(identifiant);
            pName.setText(product.getIntitule());
            pPrice.setText(product.getPrixString());
        }
    }

    public void onValider() {
        Product item;
        if(app == null)
            app = (MyApplication)getApplicationContext();

        ArrayList<Product> products = app.getProducts();

        if(identifiant < 0) {
            item = new Product();
            item.setIntitule(pName.getText().toString());
            item.setPrix(Float.parseFloat(pPrice.getText().toString()));
            item.setImgId(R.drawable.no_product);
            products.add(item);
        } else {
            item = products.get(identifiant);
            item.setIntitule(pName.getText().toString());
            item.setPrix(Float.parseFloat(pPrice.getText().toString()));
        }
    }

    // Delete Product from Products List !
    public void deleteItem() {
        if(app == null)
            app = (MyApplication)getApplicationContext();

        ArrayList<Product> products = app.getProducts();

        if(identifiant < 0) {
            Toast.makeText(app, "select product to delete !", Toast.LENGTH_SHORT).show();
        } else {
            products.remove(identifiant);
        }
    }
}
