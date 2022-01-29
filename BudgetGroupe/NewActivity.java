package com.example.colocataire_app.BudgetGroupe;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.colocataire_app.Home.MainActivity;
import com.example.colocataire_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public  class NewActivity extends AppCompatActivity {


    private Button date_button;
    private EditText edit_txt_nom;
    public static EditText edit_txt_budget;
    private EditText edit_txt_nbr;
    private TextView edit_txt_date;
    private Button save;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference reference = db.getReference().child("Activities");
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private DatePickerDialog datePickerDialog;
    private Button but;
    //datepicker
    private TextView editTextDate;
    private Button buttonDate, back;
    private CheckBox checkBoxIsSpinnerMode;
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button button;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_first);
        back=(Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        Button bu = (Button) findViewById(R.id.ajouter);
        bu.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                showmsg(v);
            }
        });
        //datepicker


        this.editTextDate = (TextView) this.findViewById(R.id.datepicker);
        this.buttonDate = (Button) this.findViewById(R.id.button_date);
        this.checkBoxIsSpinnerMode = this.findViewById(R.id.checkBox_isSpinnerMode);

        this.buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSelectDate();
            }
        });

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        this.lastSelectedYear = c.get(Calendar.YEAR);
        this.lastSelectedMonth = c.get(Calendar.MONTH);
        this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);
    }

    // User click on 'Select Date' button.
    private void buttonSelectDate() {
        final boolean isSpinnerMode = this.checkBoxIsSpinnerMode.isChecked();

        // Date Select Listener.
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                editTextDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                lastSelectedYear = year;
                lastSelectedMonth = monthOfYear;
                lastSelectedDayOfMonth = dayOfMonth;
            }
        };

        DatePickerDialog datePickerDialog = null;

        if (isSpinnerMode) {
            // Create DatePickerDialog:
            datePickerDialog = new DatePickerDialog(this,
                    android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                    dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        }
        // Calendar Mode (Default):
        else {
            datePickerDialog = new DatePickerDialog(this,
                    dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        }

        // Show
        datePickerDialog.show();
    }










    public void showmsg(View V){
        AlertDialog.Builder msg = new AlertDialog.Builder(this);
        msg.setTitle("Calcul de Budget");
        msg.setMessage("Vous voulez Calculer la part de chacun ?");
        msg.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference ref = db.getReference("Activities");


                edit_txt_nom = (EditText) findViewById(R.id.nom_activite);
                edit_txt_nbr = (EditText) findViewById(R.id.nbr_participant);
                edit_txt_date = (TextView) findViewById(R.id.datepicker);
                edit_txt_budget = (EditText) findViewById(R.id.budget_activity);


                HashMap<String, String> Activities = new HashMap<>();

                Activities.put("sname_activity", edit_txt_nom.getText().toString());
                Activities.put("sbudget_activity", edit_txt_budget.getText().toString());
                Activities.put("snbr_activity ", edit_txt_nbr.getText().toString());
                Activities.put("Activity_date", edit_txt_date.getText().toString());

                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference tasksRef = rootRef.child("Activities").push();
                tasksRef.setValue(Activities);

                FirebaseDatabase.getInstance().getReference("Activities").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).setValue(Activities);
                Toast.makeText(NewActivity.this,"Activité enregistré",
                        Toast.LENGTH_SHORT).show();

            }
        });

        msg.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent(getApplicationContext(),CalculBud.class);
                EditText nomactivity,bud;
                bud=findViewById(R.id.budget_activity);
                String budd=(String)bud.getText().toString();
                nomactivity=findViewById(R.id.nom_activite);
                String activity_name=(String)nomactivity.getText().toString();
                intent.putExtra("budget",budd);
                intent.putExtra("nomActivity",activity_name);
                startActivity(intent);
            }}
        );msg.create().show();




    }}
