package com.example.colocataire_app.BudgetPersonnelle;

public class MainModel {
    String Home,img1;
    String prix;
MainModel()
{

}
    public MainModel(String home, String img1, String prix) {
        Home = home;
        this.img1 = img1;
        this.prix = prix;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getHome() {
        return Home;
    }

    public void setHome(String home) {
        Home = home;
    }
}
