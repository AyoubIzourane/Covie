package com.example.colocataire_app.BudgetGroupe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.colocataire_app.R;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;


public class ReglementAdapter extends BaseAdapter {

    Context context;
    private final String [] names;
    private ArrayList<String> reglements;

    public ReglementAdapter(Context context, String[] names, ArrayList<String> reglements) {
        this.context = context;
        this.names = names;
        this.reglements = reglements;
    }




    @Override
    public int getCount() {

        return names.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.sigle_item_regl, parent, false);
            viewHolder.textName =  convertView.findViewById(R.id.nom_coloc);
            viewHolder.reglements =  convertView.findViewById(R.id.regl);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.textName.setText(names[position]);
        viewHolder.reglements.setText(reglements.get(position)+"\n\n");

        return convertView;
    }

    private static class ViewHolder {

        TextView textName;
        TextView reglements;

    }

}
