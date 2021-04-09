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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    private EditText firstName, lastName, email, password, confirmPassword;
    private Button createAccountBtn;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("mUsers");
        mAuth = FirebaseAuth.getInstance();
        mProgressDialog = new ProgressDialog(this);

        //cloud database
        firebaseFirestore = FirebaseFirestore.getInstance();

        firstName = (EditText) findViewById(R.id.first_name_edit_text);
        lastName = (EditText) findViewById(R.id.last_name_edit_text);
        email = (EditText) findViewById(R.id.email_edit_text_register);
        password = (EditText) findViewById(R.id.password_edit_text_register);
        confirmPassword = (EditText) findViewById(R.id.confirm_password_edit_text_register);
        createAccountBtn = (Button) findViewById(R.id.create_account_button_register);

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNameString = firstName.getText().toString().trim();
                String lastNameString = lastName.getText().toString().trim();
                String emailString = email.getText().toString().trim();
                String passwordString = password.getText().toString().trim();
                String confirmPasswordString = confirmPassword.getText().toString().trim();

                if(!( firstNameString.equals("") && lastNameString.equals("") && emailString.equals("")
                            && passwordString.equals("") && confirmPasswordString.equals("")))
                {
//                    if(passwordString != confirmPasswordString){
//                        Toast.makeText(register.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
//                    }
//                    else{
                        mProgressDialog.setMessage("Creating Account");
                        mProgressDialog.show();

                        mAuth.createUserWithEmailAndPassword(emailString,passwordString)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        if(authResult != null)
                                        {  //cloud database
                                            Map<Object, String> userdata = new HashMap<>();
                                            userdata.put("First Name", firstNameString);
                                            userdata.put("Last Name", lastNameString);

                                            firebaseFirestore.collection("USERS")
                                                    .add(userdata)
                                                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<DocumentReference> task) {
                                                                 if(task.isSuccessful()){

                                                                     mProgressDialog.dismiss();
                                                                     //now send the user to homescreen
                                                                     Intent intent = new Intent(register.this, HomeActivity.class);
                                                                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                                     startActivity(intent);
                                                                 }
                                                                 else {
                                                                     mProgressDialog.setMessage("Unsuccessful");
                                                                     mProgressDialog.show();
                                                                 }
                                                        }
                                                    });

//                                            String userid = mAuth.getCurrentUser().getUid();
//                                            DatabaseReference currentUserDb = mDatabaseReference.child(userid);//fetching UID
//                                            currentUserDb.child("First Name").setValue(firstNameString);    //making child under that UID
//                                            currentUserDb.child("Last Name").setValue(lastNameString);
//                                            currentUserDb.child("Image").setValue("none");
//
//                                            mProgressDialog.dismiss();
//                                            //now send the user to homescreen
//                                            Intent intent = new Intent(register.this, HomeActivity.class);
//                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                            startActivity(intent);
                                        }
                                    }
                                });
                 //   }
                }
                else{
                    Toast.makeText(register.this,"Fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}