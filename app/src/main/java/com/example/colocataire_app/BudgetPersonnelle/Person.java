package com.example.colocataire_app.BudgetPersonnelle;

public class Person {
    // Variable to store data corresponding
    // to firstname keyword in database
    private String Home;

    // Variable to store data corresponding
    // to lastname keyword in database
    private String date;

    // Variable to store data corresponding
    // to age keyword in database
    private String prix;

    // Mandatory empty constructor
    // for use of FirebaseUI
    public Person() {}

    // Getter and setter method
    public String getHome()
    {
        return Home;
    }
    public void setHome(String Home)
    {
        this.Home = Home;
    }
    public String getDate()
    {
        return date;
    }
    public void setDate(String date)
    {
        this.date = date;
    }
    public String getPrix()
    {
        return prix;
    }
    public void setPrix(String prix)
    {
        this.prix= prix;
    }
}