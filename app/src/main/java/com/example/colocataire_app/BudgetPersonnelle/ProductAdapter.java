package com.example.colocataire_app.BudgetPersonnelle;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.List;

/*
 * @author  : Mouad ahatour
 * @Title   : Product Adapter
 */

public class ProductAdapter extends ArrayAdapter<Product> {
    public ProductAdapter(Context ctx, List<Product> products) {
        super(ctx, 0, products);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ProductView productView = (ProductView) convertView;
        if(productView == null) {
            productView = productView.create(parent);
        }
        // TODO modifier la couleur de fond de l'item selon la parité de position
        if(position % 2 != 0) {
            productView.setBackgroundColor(Color.LTGRAY);
        }
        productView.display(super.getItem(position));
        return productView;
    }
}
