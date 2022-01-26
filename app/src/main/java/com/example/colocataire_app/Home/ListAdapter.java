package com.example.colocataire_app.Home;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.colocataire_app.R;
import com.example.colocataire_app.UserData;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class ListAdapter extends RecyclerView.Adapter {

    List<UserData> userDataList;

    public ListAdapter(List<UserData> userDataList) {
        this.userDataList = userDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item,parent,false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;
        UserData userData =userDataList.get(position);
        viewHolderClass.nom.setText(userData.getNom());
        viewHolderClass.prenom.setText(userData.getPrenom());
    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }
    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView nom,prenom;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.nom_colocataire);
            prenom =itemView.findViewById(R.id.prenom_colocataire);
        }
    }
}

