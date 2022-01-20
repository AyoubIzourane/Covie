package com.example.colocataire_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.colocataire_app.Home.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//this class used to create new acccount where user needs to put his information (for registration)

public class NewAccount extends AppCompatActivity {

    private EditText edit_txt_prenom, edit_txt_nom, edit_txt_Email, edit_txt_Pass, edit_txt_CoPass;
    private Button button_register;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    String firstName,lastName, email, password, repeated_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        //Partie Frontend
        //Button retour
        Button btn_back = (Button) findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        //Partie Backend (for registration)

        edit_txt_prenom = findViewById(R.id.prenom);
        edit_txt_nom = findViewById(R.id.nom);
        edit_txt_Email = findViewById(R.id.email);
        edit_txt_Pass = findViewById(R.id.motdepasse);
        edit_txt_CoPass = findViewById(R.id.motdepasse2);

        button_register = findViewById(R.id.login2);
        //Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserData");

        //handle user SignUp button
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateFirstName() | !validateLastName() | !validateEmail() | !validatePassword()) {
                    return;
                }
                if (password.equals(repeated_password)) {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener
                            (new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        UserData data = new UserData(firstName, lastName, email);
                                        FirebaseDatabase.getInstance().getReference("UserData")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(data).
                                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(NewAccount.this, "Inscription réussie", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(NewAccount.this, MainActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                });
                                    } else {
                                        Toast.makeText(NewAccount.this, "Vérifier l'email id ou le mot de passe", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(NewAccount.this, "Le mot de passe ne correspond pas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean validateFirstName() {
        firstName = edit_txt_prenom.getText().toString().trim();
        if (TextUtils.isEmpty(firstName)) {
            Toast.makeText(NewAccount.this, "Enter ton nom", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validateLastName() {
        lastName = edit_txt_nom.getText().toString().trim();
        if (TextUtils.isEmpty(lastName)) {
            Toast.makeText(NewAccount.this, "Enter ton prenom", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validateEmail() {
        email = edit_txt_Email.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(NewAccount.this, "Enter ton Email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(NewAccount.this, "Veuillez saisir un e-mail valide\n" +
                    "\n", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validatePassword() {
        password = edit_txt_Pass.getText().toString().trim();
        repeated_password = edit_txt_CoPass.getText().toString().toLowerCase();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(NewAccount.this, "Enter ton mot de passe", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(repeated_password)) {
            Toast.makeText(NewAccount.this, "Enter Your Co-Password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.length() < 6) {
            Toast.makeText(NewAccount.this, "Le mot de passe est très court", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}
