package com.pharma.blooddonate

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LogActivity : AppCompatActivity() {

    lateinit var mygooglesignbtn: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        mygooglesignbtn = findViewById(R.id.mygooglesignbtn)

        mygooglesignbtn.setOnClickListener(View.OnClickListener {
            val i = Intent(this, FormActivity::class.java)
            startActivity(i)
        })

    }
}