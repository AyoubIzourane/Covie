package com.example.colocataire_app.BudgetGroupe;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.colocataire_app.R;

import java.util.ArrayList;
import com.example.colocataire_app.BudgetGroupe.Reglement;
import com.example.colocataire_app.BudgetGroupe.ReglementAdapter;
public class reg extends AppCompatActivity {




        ListView lView;


        String [] names;
        ArrayList<String> regs= new ArrayList<>();
        ReglementAdapter lAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.reglement_budget);
            Bundle bundle = getIntent().getExtras();
            ArrayList<Reglement> list = (ArrayList<Reglement>) bundle.getSerializable("List");
            System.out.println(list.size());
            for (Integer i=0;i<list.size();i++){
                names[i]=list.get(i).fullname;
                regs.add(String.valueOf(list.get(i).reglements));
                System.out.println(regs.size());
            }

            lView = findViewById(R.id.listcolocsreglement);
            lAdapter = new ReglementAdapter(reg.this,names , regs);
            lView.setAdapter(lAdapter);



        }
    }

