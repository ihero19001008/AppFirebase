package com.example.appfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomActivity extends AppCompatActivity {
    Button Lout;
    //FirebaseAuth mFirebaseAuth;
    //private FirebaseAuth.AuthStateListener mAuthState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hom);

        Lout = (Button) findViewById(R.id.btnLogout);
    }

    public void Logout(View view) {
       //mFirebaseAuth.signOut();
        FirebaseAuth.getInstance().signOut();
        Intent intlogou = new Intent(HomActivity.this,LoginActivity.class);
        startActivity(intlogou);
    }
}
