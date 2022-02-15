package com.example.authentication_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextInputEditText RegEmail;
    TextInputEditText RegPass;
    TextInputEditText RegPass1;
    TextView LoginInstead;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        RegEmail = findViewById(R.id.RegEmail);
        RegPass = findViewById(R.id.RegPass);
        RegPass1 = findViewById(R.id.RegPass1);
        btnRegister = findViewById(R.id.btnRegister);
        LoginInstead = findViewById(R.id.LoginInstead);



        mAuth = FirebaseAuth.getInstance();


        btnRegister.setOnClickListener(view -> {
            createUser();
        });

        LoginInstead.setOnClickListener(view -> {
            startActivity(new Intent(Register.this,Login.class));
        });
    }
    private void createUser(){
        String email = RegEmail.getText().toString();
        String pass = RegPass.getText().toString();
        String pass1 = RegPass1.getText().toString();

        if(!pass.equals(pass1)){
            RegPass1.setError("Password do not match");
        }else if(TextUtils.isEmpty(email)){
            RegEmail.setError("Email cannot be empty");
            RegEmail.requestFocus();
        }else if(TextUtils.isEmpty(pass)){
            RegPass.setError("Email cannot be empty");
            RegPass.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Register.this,"User Registered Successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this, Login.class));
                    }else {
                        Toast.makeText(Register.this,"Error Occurred "+ Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                        System.out.println(task.getException().getMessage());
                    }
                }
            });
        }
    }


}