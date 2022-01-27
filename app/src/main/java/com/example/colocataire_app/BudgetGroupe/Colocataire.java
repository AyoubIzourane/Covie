package com.example.colocataire_app.BudgetGroupe;
import java.util.ArrayList;
import java.util.List;

public class Colocataire {
      static ArrayList<Reglement> listReglement = new ArrayList<>();
    public float getDepense() {
        return depense;
    }

    public void setDepense(float depense) {
        this.depense = depense;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float depense;
    public String nom;
    public String getNom() {
        return nom;
    }



    public static ArrayList<Reglement> remplir(ArrayList<Colocataire> house) {
        int n;
        n = house.size();
        int j, i;

        for (i = 0; i < n; i++) {

            ArrayList<String> depp = new ArrayList<>();
            String s = (house.get(i)).nom.toString() + " a depensé: " + (house.get(i)).depense;
            depp.add(s);
            for (j = 0; j < n; j++)
                if (j != i) {
                    double diff = house.get(j).depense / n - house.get(i).depense / n;
                    double d = (double) Math.round(diff * 100) / 100;

                    if (diff > 0) {
                         s = "\\-->" + (house.get(i)).nom.toString() + " doit donner à " + (house.get(j)).nom.toString() + ":" + d;

                        depp.add(s);
                    }
                }
            Reglement r = new Reglement((house.get(i)).nom, depp);
            listReglement.add(r);
        }
        System.out.println(listReglement);
        return listReglement;


    }
}