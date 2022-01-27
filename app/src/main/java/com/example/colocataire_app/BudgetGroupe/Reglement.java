package com.example.colocataire_app.BudgetGroupe;

import java.util.ArrayList;

public class Reglement {
    String fullname;

    ArrayList<String> reglements;

    public Reglement(String fullname, ArrayList<String> reglements) {
        this.fullname = fullname;

        this.reglements = reglements;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }



    public ArrayList<String> getReglements() {
        return reglements;
    }

    public void setReglements(ArrayList<String> reglements) {
        this.reglements = reglements;
    }
}
