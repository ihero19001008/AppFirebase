package com.example.appfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    public EditText email,password;
    public TextView tvSignin;
    public Button btnSignup;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.edtEmail);
        password = (EditText) findViewById(R.id.edtPassword);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        tvSignin = (TextView) findViewById(R.id.tvSignin);
        tvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                if (mail.isEmpty()){
                    email.setError("Enter your email ID");
                    email.requestFocus();
                }
                else if (pass.isEmpty()){
                    password.setError("Enter your password");
                    password.requestFocus();
                }
                else if (mail.isEmpty() && pass.isEmpty()){
                    Toast.makeText(MainActivity.this,"Fields are empty!",Toast.LENGTH_SHORT).show();
                }
                else if (pass.length()<6) {
                    password.setError("password more than 6");
                    password.requestFocus();
                }
                else if (!(mail.isEmpty()&& pass.isEmpty())) {
                    mAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                startActivity(new Intent(MainActivity.this,HomActivity.class));


                            } else if (!task.isSuccessful()){

                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this, "failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
