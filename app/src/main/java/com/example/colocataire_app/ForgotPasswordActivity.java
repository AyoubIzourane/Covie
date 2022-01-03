package com.example.colocataire_app;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText edit_txt_resetEmail;
    private Button button_resetPassword;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //Button retour
        Button btn_back = (Button) findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });


        //        get all view id from XML
        edit_txt_resetEmail = findViewById(R.id.reset_email);
        button_resetPassword = findViewById(R.id.reinitialiser);

        //        Get Firebase auth instance
        firebaseAuth = FirebaseAuth.getInstance();
        button_resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.sendPasswordResetEmail(edit_txt_resetEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgotPasswordActivity.this, "Password reset link sent to your Email", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(ForgotPasswordActivity.this, Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(ForgotPasswordActivity.this, "Something error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}