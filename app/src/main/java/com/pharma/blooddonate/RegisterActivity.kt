package com.pharma.blooddonate

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    var formbtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        formbtn = findViewById(R.id.formbtn)
        formbtn!!.setOnClickListener(View.OnClickListener {
            val i = Intent(this@RegisterActivity, DonerFragment::class.java)
            startActivity(i)
            finishAffinity()
        })
    }
    }