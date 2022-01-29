package com.example.colocataire_app.BudgetGroupe;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colocataire_app.R;
import com.example.colocataire_app.UserData;

import java.util.ArrayList;
import java.util.List;


public class ListAdapterColocBudget extends RecyclerView.Adapter {

    List<UserData> userDataList;
    static ArrayList<Colocataire>act = new ArrayList<>();
    public static ArrayList<Colocataire> getAct() {
        return act;
    }

    public static void setAct(ArrayList<Colocataire> act) {
        ListAdapterColocBudget.act = act;
    }

    public ListAdapterColocBudget(List<UserData> userDataList) {
        this.userDataList = userDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_part,parent,false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);
        return viewHolderClass;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;
        UserData userData =userDataList.get(position);
        String Firstname=userData.getNom();
        String secondname=userData.getPrenom();
        secondname = Firstname+" "+secondname;
        viewHolderClass.fullname.setText(secondname.toUpperCase());
        String finalSecondname = secondname;

        ((ViewHolderClass) holder).ck.setEnabled(false);
        ((ViewHolderClass) holder).bud.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                EditText ess = (EditText) ((ViewHolderClass) holder).bud;
                String esss = ess.getText().toString();
                if (!(esss.trim().length() < 0)) {
                    ((ViewHolderClass) holder).ck.setEnabled(true);
                    ((ViewHolderClass) holder).ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked){
                                Colocataire p = new Colocataire();
                                p.nom = finalSecondname;
                                p.depense= Float.parseFloat(String.valueOf(((ViewHolderClass) holder).bud.getText()));
                                act.add(p);}
                        }});
                }

            }
        });}




    @Override
    public int getItemCount() {
        return userDataList.size();
    }
    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView fullname,bud;
        Button butt;
        CheckBox ck;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            ck=itemView.findViewById(R.id.part);
            bud=itemView.findViewById(R.id.dep);
            fullname = itemView.findViewById(R.id.nom_coloc);
            butt=itemView.findViewById(R.id.button);


        }
    }
}

