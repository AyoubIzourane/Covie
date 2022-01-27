package com.example.colocataire_app.BudgetGroupe;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colocataire_app.R;

import java.util.List;


public class Listadapter extends RecyclerView.Adapter {

    List<MainModel> Activities;

    public Listadapter(List<MainModel> activity) {
        this.Activities = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_activities,parent,false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;
        MainModel activity =Activities.get(position);
        viewHolderClass.nbr.setText(activity.getSnbr_activity());
        viewHolderClass.nom.setText(activity.getSname_activity());
        viewHolderClass.date.setText(activity.getActivity_date());
        viewHolderClass.bud.setText(activity.getSbudget_activity());



    }

    @Override
    public int getItemCount() {
        return Activities.size();
    }
    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView nom,date,bud,nbr;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

            nom = itemView.findViewById(R.id.nom_activite);
            date = itemView.findViewById(R.id.Date_Activity);
            bud = itemView.findViewById(R.id.budget_activity);
            nbr = itemView.findViewById(R.id.nbrpar);


        }
    }
}

