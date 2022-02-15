package com.example.authentication_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    TextInputEditText LoginEmail;
    TextInputEditText LoginPass;
    Button btnLogin;
    TextView RegisterInstead;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginEmail = findViewById(R.id.LoginEmail);
        LoginPass = findViewById(R.id.LoginPass);
        btnLogin = findViewById(R.id.btnLogin);
        RegisterInstead = findViewById(R.id.RegisterInstead);
        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {
            loginUser();
        });

        RegisterInstead.setOnClickListener(view -> {
            startActivity(new Intent(Login.this,Register.class));
        });



    }
    private void loginUser(){
        String email = LoginEmail.getText().toString();
        String pass = LoginPass.getText().toString();


         if(TextUtils.isEmpty(email)){
             LoginEmail.setError("Email cannot be empty");
             LoginEmail.requestFocus();
        }else if(TextUtils.isEmpty(pass)){
             LoginPass.setError("Email cannot be empty");
             LoginPass.requestFocus();
        }else{
             mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful()){
                         Toast.makeText(Login.this,"User Logged In Successfully",Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(Login.this,MainActivity.class));
                     }else {
                         Toast.makeText(Login.this,"Some Error Occurred",Toast.LENGTH_SHORT).show();
                     }
                 }
             });
         }
    }

}