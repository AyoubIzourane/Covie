package com.example.colocataire_app;

//this class used to store users data
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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "nom= '" + nom + '\'' +
                ", prenom='" + prenom + '\'';
    }
}
