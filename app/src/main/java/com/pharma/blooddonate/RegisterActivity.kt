package com.pharma.blooddonate

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var emailet: EditText
    lateinit  var passet:EditText
    lateinit var nameet:EditText
    lateinit var gotologin: TextView
    lateinit var loginbtn: Button
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var pd: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        pd = ProgressDialog(this)
        pd.setTitle("Please Wait")
        pd.setCancelable(false)

        emailet = findViewById(R.id.emailet)
        nameet = findViewById(R.id.nameet)
        passet = findViewById(R.id.passet)
        gotologin = findViewById(R.id.gotologin)


        loginbtn = findViewById(R.id.loginbtn)
        firebaseAuth = FirebaseAuth.getInstance()


        loginbtn.setOnClickListener {
            if (nameet.text.toString().isEmpty() || emailet.text.toString()
                    .isEmpty() || passet.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Please enter all feilds", Toast.LENGTH_SHORT)
                    .show()
            } else {
                signupuser(emailet.text.toString(), passet.text.toString())
            }
        }


        gotologin.setOnClickListener {
            val i = Intent(this, LogActivity::class.java)
            startActivity(i)
        }

    }

    private fun signupuser(email: String, pass: String) {
        pd.show()
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    emailet.setText("")
                    passet.setText("")
                    nameet.setText("")
                    pd.dismiss()
                    Toast.makeText(
                        this,
                        "Signed Up Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    pd.dismiss()
                    Toast.makeText(this, "" + task.exception, Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }
}