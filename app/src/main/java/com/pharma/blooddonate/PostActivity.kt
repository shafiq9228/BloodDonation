package com.pharma.blooddonate

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class PostActivity : AppCompatActivity() {


    lateinit var spin: Spinner

    var arrayAdapter1: ArrayAdapter<String>? = null
    var planets = arrayOf(
        "A+", "A-", "B+",
        "B-", "O+", "O-", "AB+", "AB-"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)


        spin = findViewById(R.id.spin)

        arrayAdapter1 =
            ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_dropdown_item, planets)

        spin.setAdapter(arrayAdapter1)


        spin.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })


    }
}