package com.pharma.blooddonate

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    var donerbtn: Button? = null
    var rvbtn: Button? = null
    lateinit var sh: SharedPreferences
    lateinit var auth: FirebaseAuth
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        donerbtn = findViewById(R.id.donerbtn)
        rvbtn = findViewById(R.id.rvbtn)
        donerbtn!!.setOnClickListener(View.OnClickListener {
            editor.putInt("type", 0)
            editor.apply()
            editor.commit()
            val i = Intent(this@MainActivity, google::class.java)
            i.putExtra("type", 0)
            startActivity(i)
        })

        sh = getSharedPreferences("mypref", MODE_PRIVATE)
        editor = sh.edit()

        auth = FirebaseAuth.getInstance()

        rvbtn?.setOnClickListener(View.OnClickListener {

            editor.putInt("type", 1)
            editor.apply()
            editor.commit()
            val i = Intent(this@MainActivity, google::class.java)
            i.putExtra("type", 1)
            startActivity(i)
        })
    }


    override fun onStart() {
        super.onStart()

        if (auth.currentUser != null){
            if (sh.getInt("type",2)  == 0 ){

                val intent = Intent(this, DonerFragment::class.java)
                startActivity(intent)
                finish()
            }else  if (sh.getInt("type",2)  == 1 ){
                val intent = Intent(this, MyFragment::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}