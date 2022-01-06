package com.example.colocataire_app.BudgetPersonnelle;


import android.app.Application;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.example.colocataire_app.R;

import java.util.ArrayList;

/*
 * @author  : Mouad ahatour
 * @Title   : Application
 */

public class MyApplication extends Application {
    private ArrayList<Product> products;

    public MyApplication () {
        this.products = new ArrayList<>();
    }
    public ArrayList<Product> getProducts() {
        return this.products;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.products = initData();
    }

    public ArrayList<Product> initData() {
        this.products.clear();
        Resources res = getResources();
        final String[] intitule = getResources().getStringArray(R.array.intitules);
        final String[] prix = getResources().getStringArray(R.array.prix);
        TypedArray images = getResources().obtainTypedArray(R.array.idimages);

        for(int i=0; i<intitule.length; i++) {
            products.add(new Product(intitule[i], Float.parseFloat(prix[i]), images.getResourceId(i,0)));
        }
        return products;
    }
}
