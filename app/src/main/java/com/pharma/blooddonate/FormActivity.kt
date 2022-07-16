package com.pharma.blooddonate

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FormActivity : AppCompatActivity() {

    lateinit var bloodet : EditText
    lateinit var mobileet : EditText
    lateinit var addresset : EditText
    lateinit var ageet : EditText
    lateinit var malecb : CheckBox
    lateinit var femalecb : CheckBox
    lateinit var db: FirebaseFirestore
    lateinit var gender: String
    lateinit var submitbtn: Button
    lateinit var firebaseauth: FirebaseAuth
    lateinit var pd: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        pd = ProgressDialog(this)
        pd.setTitle("Please Wait")
        pd.setCancelable(false)

        bloodet = findViewById(R.id.bloodet)
        mobileet = findViewById(R.id.mobileet)
        addresset = findViewById(R.id.addresset)
        ageet = findViewById(R.id.ageet)
        malecb = findViewById(R.id.malecb)
        femalecb = findViewById(R.id.femailcb)
        submitbtn = findViewById(R.id.submitbtn)
        firebaseauth = FirebaseAuth.getInstance()

        db = FirebaseFirestore.getInstance()

        gender = "male"

        malecb.setOnClickListener(View.OnClickListener {
            malecb.isChecked = true
            femalecb.isChecked = false
        })

        femalecb.setOnClickListener(View.OnClickListener {
            malecb.isChecked = false
            femalecb.isChecked = true
        })


        if (malecb.isChecked){
            gender = "male"
        }
        else{
            gender = "female"
        }


        submitbtn.setOnClickListener(View.OnClickListener {
            val i = Intent(this, Drawer3Activity::class.java)
            startActivity(i)
            finishAffinity()
        })


    }


//    fun senddonerdetails(){
//        pd.show()
//        val details = hashMapOf<String, Any>(
//            "bloodgroup" to bloodet.text.toString(),
//            "address" to addresset.text.toString(),
//            "gender" to gender,
//            "age" to ageet.text.toString(),
//            "mobile" to mobileet.text.toString(),
//            "uid" to firebaseauth.currentUser?.uid.toString()
//        )
//
//
//        db.collection("doner")
//            .document(""+ firebaseauth.currentUser?.uid.toString())
//            .set(details)
//            .addOnCompleteListener(OnCompleteListener { task ->
//                if (task.isSuccessful){
//                    Toast.makeText(applicationContext, "Form Submitted", Toast.LENGTH_SHORT).show()
//                    db.collection("users")
//                        .document(firebaseauth.currentUser?.uid.toString())
//                        .update("submitted", true)
//                        .addOnCompleteListener(OnCompleteListener {task1 ->
//                            if (task1.isSuccessful){
//                                pd.dismiss()
//
//                            }
//                            else{
//                                Toast.makeText(applicationContext, "Failed "+task1.exception, Toast.LENGTH_SHORT).show()
//                                pd.dismiss()
//                            }
//
//                        })
//                }
//                else{
//                    pd.dismiss()
//                    Toast.makeText(applicationContext, "Failed "+task.exception, Toast.LENGTH_SHORT).show()
//                }
//            })
//    }
}