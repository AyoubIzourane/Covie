package com.example.colocataire_app.BudgetPersonnelle;


import com.example.colocataire_app.R;

public class Product {
    public String intitule;
    public float prix;
    public int imgId;

    // Initializing the product !
    public Product () {
        this.intitule = "default";
        this.prix = 0;
        this.imgId = R.drawable.no_product;
    }

    public Product (String intitule, float prix, int idImg) {
        this.intitule = intitule;
        this.prix = prix;
        this.imgId = idImg;
    }

    // Getters & Setters !
    public String getIntitule() {
        return intitule;
    }
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    public float getPrix() {
        return prix;
    }
    public void setPrix(float prix) {
        this.prix = prix;
    }
    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "intitule='" + intitule + '\'' +
                ", prix=" + prix +
                ", imgId=" + imgId +
                '}';
    }

    // Convert product price to String !
    public String getPrixString () {
        return Float.toString(prix);
    }
}

