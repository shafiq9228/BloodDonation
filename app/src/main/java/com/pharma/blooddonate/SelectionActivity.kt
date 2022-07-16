package com.pharma.blooddonate

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SelectionActivity : AppCompatActivity() {
   lateinit var firebaseAuth: FirebaseAuth

   lateinit var sh: SharedPreferences
   lateinit var editor: SharedPreferences.Editor
    lateinit var donerbtn: Button
    lateinit  var receiverbtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)

        sh = getSharedPreferences("mypref", MODE_PRIVATE)
        editor = sh.edit()

        firebaseAuth = FirebaseAuth.getInstance()

        donerbtn = findViewById(R.id.donerbtn)
        receiverbtn = findViewById(R.id.receiverbtn)


        donerbtn.setOnClickListener {
            val i = Intent(this, LogActivity::class.java)
            editor.putInt("type", 0)
            editor.apply()
            editor.commit()
            startActivity(i)
        }

        receiverbtn.setOnClickListener {
            val i = Intent(this, LogActivity::class.java)
            editor.putInt("type", 1)
            editor.apply()
            editor.commit()
            startActivity(i)
        }


    }

    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser != null){
//            val i = Intent(this, DashBoardActivity::class.java)
//            startActivity(i)
//            finish()
        }
    }
}