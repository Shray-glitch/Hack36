package com.example.registerfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseAuth.AuthStateListener mAuhListener;
    private EditText email,password;
    private Button login, createAccountBtn, btnProduct;
    //private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email_edit_text);
        password = (EditText) findViewById(R.id.password_edit_text);
        login = (Button) findViewById(R.id.login_button);
        createAccountBtn = (Button) findViewById(R.id.create_account_button);

        //Initializing firebase
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        mAuhListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if(user != null){
                    //user is signed in
                    Toast.makeText(com.example.registerfirebase.login.this, "Signed In",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, HomeActivity.class);
                    startActivity(intent);
                }else{
                    //signed out
                    Toast.makeText(com.example.registerfirebase.login.this, "Not Signed In",Toast.LENGTH_SHORT).show();
                }
            }
        };

//        btnProduct = (Button)findViewById(R.id.product_details);
//
//        btnProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent productDetailsIntent = new Intent(btnProduct.getContext(), ProductDetailsActivity.class);
//                btnProduct.getContext().startActivity(productDetailsIntent);
//            }
//        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();

                if(!emailString.equals("") && !passwordString.equals(""))
                {
//                    progressDialog.setMessage("Logging In");
//                    progressDialog.show();

                    login(emailString,passwordString);

                    //progressDialog.dismiss();
                    Intent intent = new Intent(login.this, HomeActivity.class);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    if(emailString.equals("")){
                        Toast.makeText(com.example.registerfirebase.login.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                    }
                    else if(passwordString.equals("")){
                        Toast.makeText(com.example.registerfirebase.login.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, register.class));
            }
        });
    }

    private void login(String emailString, String passwordString) {

        mAuth.signInWithEmailAndPassword(emailString, passwordString)
                .addOnCompleteListener(com.example.registerfirebase.login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(com.example.registerfirebase.login.this, "Successful",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(com.example.registerfirebase.login.this, "Unsuccessful",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuhListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(mAuhListener != null){
            mAuth.removeAuthStateListener(mAuhListener);
        }
    }
}