package com.example.registerfirebase;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class myAccount extends AppCompatActivity {
    private Button logoutBtn;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        logoutBtn = (Button) findViewById(R.id.logout_button);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAuth != null && mUser != null){
                    mAuth.signOut();
                    Intent intent = new Intent(myAccount.this, login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );  //to clear stack of activities
                    startActivity(intent);

                }
            }
        });
    }
}