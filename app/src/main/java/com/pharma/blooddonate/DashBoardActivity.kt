package com.pharma.blooddonate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class DashBoardActivity : AppCompatActivity() {

    lateinit var addpost: Button
    lateinit var viewpost: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        addpost = findViewById(R.id.addpost)
        viewpost = findViewById(R.id.viewpost)

        viewpost.setOnClickListener(View.OnClickListener {
            val i = Intent(this, PostListActivity::class.java)

            startActivity(i)
        })

        addpost.setOnClickListener(View.OnClickListener {
            val i = Intent(this, PostActivity::class.java)

            startActivity(i)
        })
    }
}