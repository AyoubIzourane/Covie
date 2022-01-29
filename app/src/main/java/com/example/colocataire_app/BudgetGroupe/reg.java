package com.example.colocataire_app.BudgetGroupe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.colocataire_app.R;

import java.util.ArrayList;

public class reg extends AppCompatActivity {

    String[] names ;
    ArrayList<String> regs = new ArrayList<>();
    ListView lView;
    ArrayList<Colocataire> activity;
    ReglementAdapter lAdapter;
    ArrayList<Reglement> regl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reglement_budget);
        lView = (ListView) findViewById(R.id.listcolocsreglement);

        activity=ListAdapterColocBudget.getAct();
        names=new String[activity.size()];

        regl=Colocataire.remplir(activity);

        for (Integer i=0;i<regl.size();i++) {
            names[i]=regl.get(i).fullname.toString();
            regs.add((regl.get(i).reglements.toString()));
        }



        lAdapter = new ReglementAdapter(reg.this, names, regs);

        lView.setAdapter(lAdapter);


    }
}