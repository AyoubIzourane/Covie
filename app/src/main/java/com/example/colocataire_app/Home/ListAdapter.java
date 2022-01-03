package com.example.colocataire_app.Home;


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


public class ListAdapter extends BaseAdapter {

    Context context;
    private final String [] names;
    private final int [] images;

    public ListAdapter(Context context, String [] names, int [] images){
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        this.context = context;
        this.names = names;
        this.images = images;
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
            convertView = inflater.inflate(R.layout.single_list_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.nom_colocataire);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.image_colocataire);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtName.setText(names[position]);
        viewHolder.icon.setImageResource(images[position]);

        return convertView;
    }

    private static class ViewHolder {

        TextView txtName;
        ImageView icon;

    }

}

