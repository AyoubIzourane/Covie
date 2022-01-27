package com.example.colocataire_app.BudgetGroupe;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.colocataire_app.R;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;


public class ReglementAdapter extends BaseAdapter {
    Context context;
    private final String [] names;
    private final ArrayList<String>regs;

    public ReglementAdapter(Context context,String[] names,ArrayList<String> regs) {
        this.context=context;
        this.regs=regs;
        this.names = names;

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
            LayoutInflater inflater;
            inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.single_list_item, parent, false);
            viewHolder.names = convertView.findViewById(R.id.nom_coloc);
            ViewHolder.regl = convertView.findViewById(R.id.regl);



            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.names.setText(names[position]);
        viewHolder.regl.setText(regs.get(position));



        return convertView;
    }

    private static class ViewHolder {


        TextView names;
        static TextView regl;

    }

}
