package com.example.sampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /// Moving to dashboard

//        Button validationButton = findViewById(R.id.loginbtn);
//        validationButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent moveToDashboard = new Intent(MainActivity.this, Dashboard.class);
//                startActivity(moveToDashboard);
//
//                /// Success Toast
//                Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_SHORT).show();
//            }
//        });



        // Moving from one screen to the other i.e login to signup
        // Find the Text and set a click listener
        TextView signUpText = findViewById(R.id.signupText);
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to SecondActivity
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

         EditText emailField, passwordField;
         Button loginButton;
         FirebaseAuth firebaseAuth;

        emailField = findViewById(R.id.usernameLog);
        passwordField = findViewById(R.id.passwordLog);
        loginButton = findViewById(R.id.loginbtn);
        firebaseAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(v -> {
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                            // Redirect to dashboard
                            Intent moveToDashboard = new Intent(MainActivity.this, Dashboard.class);
                            startActivity(moveToDashboard);

                        } else {
                            Toast.makeText(this, "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });


    }
}