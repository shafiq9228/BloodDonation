package com.pharma.blooddonate

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class MainActivity : AppCompatActivity() {
    var donerbtn: Button? = null
    var rvbtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        donerbtn = findViewById(R.id.donerbtn)
        rvbtn = findViewById(R.id.rvbtn)
        donerbtn!!.setOnClickListener(View.OnClickListener {
            val i = Intent(this@MainActivity, google::class.java)
            startActivity(i)
        })


        rvbtn?.setOnClickListener(View.OnClickListener {
            val i = Intent(this@MainActivity, google::class.java)
            startActivity(i)
        })
    }


    override fun onStart() {
        super.onStart()
      val  googleSignInClient = GoogleSignIn.getClient(
            this@MainActivity, GoogleSignInOptions.DEFAULT_SIGN_IN
        )
        googleSignInClient.signOut()
    }
}