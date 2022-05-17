package com.pharma.blooddonate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class LoginActivity extends AppCompatActivity {

    EditText logemail, logpass;
    TextView gotosignup;
    Button loginbtn;
    FirebaseAuth firebaseAuth;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        pd = new ProgressDialog(this);
        pd.setTitle("Please Wait");
        pd.setCancelable(false);

        firebaseAuth = FirebaseAuth.getInstance();
        logemail = findViewById(R.id.logemailet);
        logpass = findViewById(R.id.logpasset);
        loginbtn = findViewById(R.id.signinbtn);
        gotosignup = findViewById(R.id.gotosignup);

        gotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (logemail.getText().toString().isEmpty() || logpass.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please Enter All Feilds", Toast.LENGTH_SHORT).show();
                }
                else {
                    loginuser(logemail.getText().toString(), logpass.getText().toString());
                }

            }
        });


    }

    private void loginuser(String email, String pass) {
        pd.show();
        firebaseAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            pd.dismiss();
                            logemail.setText("");
                            logpass.setText("");
                            Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            pd.dismiss();
                            Toast.makeText(LoginActivity.this, "Failed "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}