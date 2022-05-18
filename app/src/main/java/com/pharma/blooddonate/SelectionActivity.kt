package com.pharma.blooddonate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SelectionActivity : AppCompatActivity() {
   lateinit var firebaseAuth: FirebaseAuth

    lateinit var donerbtn: Button
    lateinit  var receiverbtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)


        firebaseAuth = FirebaseAuth.getInstance()

        donerbtn = findViewById(R.id.donerbtn)
        receiverbtn = findViewById(R.id.receiverbtn)


        donerbtn.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            i.putExtra("type", "doner")
            startActivity(i)
        }

        receiverbtn.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            i.putExtra("type","reciever")
            startActivity(i)
        }


    }

    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser != null){
            val i = Intent(this, DashBoardActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}