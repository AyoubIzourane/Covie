package com.example.colocataire_app.BudgetGroupe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colocataire_app.R;
import com.example.colocataire_app.UserData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalculBud extends AppCompatActivity {

    private TextView user_name;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference ;
    String username;
    EditText bud;
    CheckBox ck;
    List<UserData> userDataList;
    ListAdapterColocBudget listAdapter;
    RecyclerView recyclerView;
    ArrayList<Colocataire> activity=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_part);
        bud=findViewById(R.id.dep);

        Button button = findViewById(R.id.button);
        Intent intent = this.getIntent();
        String Activity_name= intent.getStringExtra("nomActivity");
        Float bu= Float.valueOf(intent.getStringExtra("budget"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activity=ListAdapterColocBudget.getAct();


                float sum=0;
                for (int i=0;i<activity.size();i++){
                    sum=sum+activity.get(i).depense;
                }
                if (sum < bu || sum > bu){
                    if (sum==0){
                        for (int i=0;i<activity.size();i++){
                            activity.get(i).depense=bu/activity.size();
                        }
                    }
                    else{
                        Toast.makeText(CalculBud.this,"La somme de vos dépot n'est pas égale au budget",
                                Toast.LENGTH_SHORT).show();}
                }

                ArrayList<Reglement> reg = new ArrayList<>();
                reg=Colocataire.remplir(activity);
                System.out.println(reg);
                Bundle bundle=new Bundle();
                bundle.putSerializable("List",reg);
                Intent intent1=new Intent(CalculBud.this,reg.class);
                intent1.putExtras(bundle);
                startActivity(intent1);

            }
        });

        user_name = findViewById(R.id.username);
        mAuth= FirebaseAuth.getInstance();
        username = mAuth.getUid();
        recyclerView = findViewById(R.id.listcolocs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userDataList =new ArrayList<>();
        TextView name;
        name=findViewById(R.id.ActivityTitle);
        name.setText(Activity_name);
        databaseReference = FirebaseDatabase.getInstance().getReference("UserData");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    UserData data = dataSnapshot.getValue(UserData.class);
                    userDataList.add(data);
                }
                listAdapter = new ListAdapterColocBudget(userDataList);
                recyclerView.setAdapter(listAdapter);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}


