package com.example.colocataire_app;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colocataire_app.Home.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText user_name, pass_word;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView btn_forgot_password = (TextView) findViewById(R.id.forgotpassword);
        btn_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        user_name=findViewById(R.id.email);
        pass_word=findViewById(R.id.password);
        Button btn_login = findViewById(R.id.login);
        TextView btn_sign = findViewById(R.id.signin);
        mAuth=FirebaseAuth.getInstance();
        btn_login.setOnClickListener(v -> {
            String email= user_name.getText().toString().trim();
            String password=pass_word.getText().toString().trim();
            if(email.isEmpty())
            {
                user_name.setError("Email est vide");
                user_name.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                user_name.setError("Entrez l'email valide");
                user_name.requestFocus();
                return;
            }
            if(password.isEmpty())
            {
                pass_word.setError("mot de passe est vide");
                pass_word.requestFocus();
                return;
            }
            if(password.length()<6)
            {
                pass_word.setError("La longueur du mot de passe est supérieure à 6");
                pass_word.requestFocus();
                return;
            }
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful())
                {
                    startActivity(new Intent(Login.this, MainActivity.class));
                }
                else
                {
                    Toast.makeText(Login.this,
                            "Veuillez vérifier vos identifiants de connexion",
                            Toast.LENGTH_SHORT).show();
                }

            });
        });
        btn_sign.setOnClickListener(v -> startActivity(new Intent(Login.this,NewAccount.class )));
    }
}







