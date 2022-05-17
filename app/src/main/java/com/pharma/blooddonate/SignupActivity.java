package com.pharma.blooddonate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    
    EditText emailet, passet, nameet;
    TextView gotologin;
    Button loginbtn;
    FirebaseAuth firebaseAuth;
    ProgressDialog pd;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        pd = new ProgressDialog(this);
        pd.setTitle("Please Wait");
        pd.setCancelable(false);

        emailet = findViewById(R.id.emailet);
        nameet = findViewById(R.id.nameet);
        passet = findViewById(R.id.passet);
        gotologin = findViewById(R.id.gotologin);

        
        loginbtn = findViewById(R.id.loginbtn);
        firebaseAuth = FirebaseAuth.getInstance();
        
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nameet.getText().toString().isEmpty() || emailet.getText().toString().isEmpty() || passet.getText().toString().isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please enter all feilds", Toast.LENGTH_SHORT).show();
                }
                else {
                    signupuser(emailet.getText().toString(), passet.getText().toString());
                }

            }
        });


        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        
    }

    private void signupuser(String email, String pass) {
        pd.show();

        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            emailet.setText("");
                            passet.setText("");
                            nameet.setText("");
                            pd.dismiss();
                            Toast.makeText(SignupActivity.this, "Signed Up Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            pd.dismiss();
                            Toast.makeText(SignupActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        

    }
}