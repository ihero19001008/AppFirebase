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

public class LoginActivity extends AppCompatActivity {

    public EditText email,password;
    public TextView tvSignup;
    public Button btnSignin;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.edtEmail1);
        password = (EditText) findViewById(R.id.edtPassword1);
        btnSignin = (Button) findViewById(R.id.btnSignup);
        tvSignup = (TextView) findViewById(R.id.tvSignin);
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        mAuthState = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                if(mFirebaseUser != null) {
                    Toast.makeText(LoginActivity.this,"You are logged in",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this,HomActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this,"Login, please!!!",Toast.LENGTH_LONG).show();
                }
            }
        };
       btnSignin .setOnClickListener(new View.OnClickListener() {
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
                   Toast.makeText(LoginActivity.this,"Fields are empty!",Toast.LENGTH_SHORT).show();
               }
               else if (pass.length()<6) {
                   password.setError("password more than 6");
                   password.requestFocus();
               }
               else if (!(mail.isEmpty()&& pass.isEmpty())) {
                   mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()){
                               Intent intentHome = new Intent(LoginActivity.this,HomActivity.class);
                               startActivity(intentHome);
                           }
                           else if (!task.isSuccessful()){
                               Toast.makeText(LoginActivity.this, "Authentication failed.",
                                       Toast.LENGTH_SHORT).show();
                           }

                       }
                   });
               }
               else {
                   Toast.makeText(LoginActivity.this, "failed.",
                           Toast.LENGTH_SHORT).show();
               }

           }
       });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthState);
    }
}
