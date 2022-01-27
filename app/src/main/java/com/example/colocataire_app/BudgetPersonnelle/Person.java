package com.example.colocataire_app.BudgetPersonnelle;

public class Person {
    // Variable to store data corresponding
    // to firstname keyword in database
    private String Home;

    // Variable to store data corresponding
    // to lastname keyword in database
    private String Date;

    // Variable to store data corresponding
    // to age keyword in database
    private String Prix;

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
        return Date;
    }
    public void setDate(String Date)
    {
        this.Date = Date;
    }
    public String getPrix()
    {
        return Prix;
    }
    public void setPrix(String Prix)
    {
        this.Prix= Prix;
    }

    @Override
    public String toString() {
        return "Person{" +
                "Home='" + Home + '\'' +
                ", date='" + Date + '\'' +
                ", prix='" + Prix + '\'' +
                '}';
    }
}