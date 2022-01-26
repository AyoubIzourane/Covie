package com.example.colocataire_app.BudgetPersonnelle;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colocataire_app.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class personAdapter extends FirebaseRecyclerAdapter<
        Person, personAdapter.personsViewholder> {

    public personAdapter(
            @NonNull FirebaseRecyclerOptions<Person> options)
    {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @SuppressLint("RecyclerView")
    @Override
    protected void
    onBindViewHolder(@NonNull personsViewholder holder,final int position, @NonNull Person model)
    {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.Home.setText(model.getHome());

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.Date.setText(model.getDate());

        // Add age from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.Prix.setText(model.getPrix());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             final DialogPlus dialogPlus= DialogPlus.newDialog(holder.Home.getContext())
                     .setContentHolder(new ViewHolder(R.layout.update_popup))
                     .setExpanded(true,1200)
                     .create();
            // dialogPlus.show();
                View view = dialogPlus.getHolderView();
                EditText Home = view.findViewById(R.id.txtName);
                EditText Date = view.findViewById(R.id.txtDate);
                EditText Prix = view.findViewById(R.id.txtPrix);
                Button btnUpdate= view.findViewById(R.id.btnUpdate);

                Home.setText(model.getHome());
                Date.setText(model.getDate());
                Prix.setText(model.getPrix());

                dialogPlus.show();
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map= new HashMap<>();
                        map.put("Home",Home.getText().toString());
                        map.put("Date",Date.getText().toString());
                        map.put("Prix",Prix.getText().toString());
                        FirebaseDatabase.getInstance().getReference().child("BudgetPerso")
                        .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.Home.getContext(), "Data Update Successully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure( Exception e) {
                                        Toast.makeText(holder.Home.getContext(), "Error", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

      holder.btnDelete.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              AlertDialog.Builder builder = new AlertDialog.Builder(holder.Home.getContext());
              builder.setTitle("Sur");
              builder.setMessage("budget sera supprimer");

               builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                   FirebaseDatabase.getInstance().getReference().child("BudgetPerso")
                           .child(getRef(position).getKey()).removeValue();
                   }
               });
              builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      Toast.makeText(holder.Home.getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                  }
              });
             builder.show();
          }
      });


    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public personsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person, parent, false);
        return new personAdapter.personsViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class personsViewholder extends RecyclerView.ViewHolder {
        TextView Home, Date, Prix;
        Button btnEdit,btnDelete;
        public personsViewholder(@NonNull View itemView)
        {
            super(itemView);

            Home = itemView.findViewById(R.id.Home);
            Date = itemView.findViewById(R.id.date);
            Prix = itemView.findViewById(R.id.prix);
            btnEdit=(Button)itemView.findViewById(R.id.btnEdit);
            btnDelete=(Button)itemView.findViewById(R.id.btnDelete);
        }
    }
}