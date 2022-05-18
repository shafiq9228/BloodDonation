package com.pharma.blooddonate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class DashBoardActivity : AppCompatActivity() {

    lateinit var addpost: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        addpost = findViewById(R.id.addpost)

        addpost.setOnClickListener(View.OnClickListener {
            val i = Intent(this, PostActivity::class.java)

            startActivity(i)
        })
    }
}