package com.example.colocataire_app.BudgetPersonnelle;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.colocataire_app.R;

/*
 * @author  : Mouad ahatour
 * @Title   : Product View
 */

public class ProductView extends RelativeLayout {
    private ImageView img;
    private TextView intitule;
    private TextView prix;

    private void findViews() {
        this.img = (ImageView)findViewById(R.id.item_product_image);
        this.intitule = (TextView)findViewById(R.id.item_product_intitule);
        this.prix = (TextView)findViewById(R.id.item_product_prix);
    }

    public static ProductView create (ViewGroup parent) {
        // get access to the xml file !
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        // create view !
        ProductView itemView = (ProductView)li.inflate(R.layout.item, parent,false);
        itemView.findViews();
        return itemView;
    }

    public void display(final Product product) {
        img.setImageResource(product.getImgId());
        intitule.setText(product.getIntitule());
        prix.setText(product.getPrixString());
    }

    public ProductView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProductView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
