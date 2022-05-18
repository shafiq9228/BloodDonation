package com.pharma.blooddonate

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LogActivity : AppCompatActivity() {


    lateinit var logemail: EditText
    lateinit  var logpass:EditText
    lateinit var gotosignup: TextView
    lateinit var loginbtn: Button
     lateinit var firebaseAuth: FirebaseAuth
    lateinit var pd: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)


        pd = ProgressDialog(this)
        pd.setTitle("Please Wait")
        pd.setCancelable(false)

        firebaseAuth = FirebaseAuth.getInstance()
        logemail = findViewById(R.id.logemailet)
        logpass = findViewById(R.id.logpasset)
        loginbtn = findViewById(R.id.signinbtn)
        gotosignup = findViewById(R.id.gotosignup)

        gotosignup.setOnClickListener(View.OnClickListener { finish() })

        loginbtn.setOnClickListener(View.OnClickListener {
            if (logemail.getText().toString().isEmpty() || logpass.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please Enter All Feilds", Toast.LENGTH_SHORT)
                    .show()
            } else {
                loginuser(logemail.getText().toString(), logpass.getText().toString())
            }
        })

    }

    private fun loginuser(email: String, pass: String) {
        pd.show()
        firebaseAuth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    pd.dismiss()
                    logemail.setText("")
                    logpass.setText("")
                    Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show()
                } else {
                    pd.dismiss()
                    Toast.makeText(
                        this,
                        "Failed " + task.exception,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
    }
}