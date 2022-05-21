package com.pharma.blooddonate

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class RegisterActivity : AppCompatActivity() {

    lateinit var emailet: EditText
    lateinit  var passet:EditText
    lateinit var nameet:EditText
    lateinit var gotologin: TextView
    lateinit var loginbtn: Button
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var pd: ProgressDialog
    lateinit var db: FirebaseFirestore
    lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        pd = ProgressDialog(this)
        pd.setTitle("Please Wait")
        pd.setCancelable(false)

        db = FirebaseFirestore.getInstance()
        emailet = findViewById(R.id.emailet)
        nameet = findViewById(R.id.nameet)
        passet = findViewById(R.id.passet)
        gotologin = findViewById(R.id.gotologin)

        type = intent.getStringExtra("type").toString()


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


                    val user = FirebaseAuth.getInstance().currentUser

                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(nameet.text.toString())
                        .build()

                    user!!.updateProfile(profileUpdates)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                senddetailstodb()
                            }
                            else{
                              Toast.makeText(applicationContext, "Failed "+task.exception, Toast.LENGTH_SHORT).show()
                            }
                        }


                } else {
                    pd.dismiss()
                    Toast.makeText(this, "" + task.exception, Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun senddetailstodb() {


        val user = mutableMapOf<String, Any>(

            "email" to emailet.text.toString(),
            "name" to nameet.text.toString(),
            "submitted" to false


        )

        db.collection("users")
            .document(""+firebaseAuth.currentUser?.uid)
            .set(user)
            .addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful){

                emailet.setText("")
                passet.setText("")
                nameet.setText("")
                pd.dismiss()

                if (type.equals("doner")){
                    val i = Intent(this, FormActivity::class.java)
                    startActivity(i)
                }

            Toast.makeText(applicationContext, "User Added", Toast.LENGTH_SHORT).show()
            }
                else{
                Toast.makeText(applicationContext, "Failed "+task.exception, Toast.LENGTH_SHORT).show()
                pd.dismiss()
            }

            })
    }



}