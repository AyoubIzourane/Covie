package com.example.colocataire_app.BudgetGroupe;

public class MainModel {
    String sname_activity,sbudget_activity,snbr_activity,Activity_date;
    MainModel(){

    }


    public MainModel(String name_activity, String budget_activity, String nbr_activity, String Activity_date) {
        this.sname_activity = name_activity;
        this.sbudget_activity = budget_activity;
        this.snbr_activity = nbr_activity;
        this.Activity_date = Activity_date;

    }




    public void setSname_activity(String sname_activity) {
        this.sname_activity = sname_activity;
    }

    public void setSbudget_activity(String sbudget_activity) {
        this.sbudget_activity = sbudget_activity;
    }

    public void setSnbr_activity(String snbr_activity) {
        this.snbr_activity = snbr_activity;
    }

    public void setActivity_date(String activity_date) {
        Activity_date = activity_date;
    }

    public String getSname_activity() {
        return sname_activity;
    }

    public String getSbudget_activity() {
        return sbudget_activity;
    }

    public String getSnbr_activity() {
        return snbr_activity;
    }


    public String getActivity_date() {
        return Activity_date;
    }

}
