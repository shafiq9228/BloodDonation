package com.pharma.blooddonate

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class DetailPage : AppCompatActivity() {

    lateinit var sh: SharedPreferences
    lateinit var chatbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)

        sh = getSharedPreferences("mypref", MODE_PRIVATE)


        chatbtn = findViewById(R.id.chatbtn)

        if (sh.getInt("type", -1) == 0){

            chatbtn.visibility = View.VISIBLE

        }
        chatbtn.setOnClickListener(View.OnClickListener {
            val i = Intent(this, ChatActivity::class.java)
            startActivity(i)
        })

    }
}