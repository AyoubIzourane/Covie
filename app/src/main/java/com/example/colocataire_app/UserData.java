package com.example.colocataire_app;

public class UserData {
    public String nom,prenom,email;
    public UserData(){
    }
    public UserData(String nom, String prenom,String email) {
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
    }
    public String getNom(){
        return nom;
    }
}
