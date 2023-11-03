package com.example.evently;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView signInTextView = findViewById(R.id.signIn);
        MaterialButton registerButton = findViewById(R.id.registerBtn);

        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the LoginActivity (Sign In screen)
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add registration logic here or navigate to the registration process
                // For example, you can open a registration form or perform registration actions.
                // You would replace the next line with the relevant code for your registration process.
                 Intent intent = new Intent(SignUpActivity.this, Bottom_nav.class);
                 startActivity(intent);
            }
        });
    }
}