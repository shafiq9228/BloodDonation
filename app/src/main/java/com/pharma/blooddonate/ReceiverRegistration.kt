package com.pharma.blooddonate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class ReceiverRegistration : AppCompatActivity() {

    var formbtn: Button? = null
    lateinit var firstname: EditText
    lateinit var lastname: EditText
    lateinit var number: EditText
    lateinit var email: EditText
    lateinit var group: EditText
    lateinit var malecb: CheckBox
    lateinit var femalecb: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiver_registration)


        firstname = findViewById(R.id.firstname)
        lastname = findViewById(R.id.lastname)
        number = findViewById(R.id.number)
        email =  findViewById(R.id.email)
        group = findViewById(R.id.group)
        formbtn = findViewById(R.id.formbtn)

        malecb = findViewById(R.id.malecb)
        femalecb = findViewById(R.id.femalecb)
        malecb.setOnClickListener(View.OnClickListener {
            femalecb.isChecked = false
            malecb.isChecked = true
        })

        femalecb.setOnClickListener(View.OnClickListener {
            femalecb.isChecked = true
            malecb.isChecked = false
        })




        formbtn!!.setOnClickListener(View.OnClickListener {

            if (firstname.text.toString() == "" || lastname.text.toString() == "" || number.text.toString()==""|| email.text.toString() == "" || group.text.toString() == ""){
                Toast.makeText(applicationContext, "Please Fill All Feilds", Toast.LENGTH_SHORT).show()
            }else{
                val i = Intent(this@ReceiverRegistration, MyFragment::class.java)
                startActivity(i)
                finishAffinity()
            }

        })


    }
}