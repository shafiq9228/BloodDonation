package com.pharma.blooddonate

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class DonerFragment : AppCompatActivity() {
    var formbtn: Button? = null
    var maindisplay: LinearLayout? = null
    var btn1: LinearLayout? = null
    var btn2: LinearLayout? = null
    var btn3: LinearLayout? = null
    var btn4: LinearLayout? = null
    var btn5: LinearLayout? = null
    var fm: FragmentManager? = null
    var ft: FragmentTransaction? = null
    var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doner_fragment)

        maindisplay = findViewById(R.id.maindisplay)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        fragment = DonerHome()
        fm = supportFragmentManager
        ft = fm!!.beginTransaction()
        ft!!.replace(R.id.maindisplay, fragment!!)
        ft!!.commit()
        btn1?.setOnClickListener(View.OnClickListener {
            fragment = DonerHome()
            fm = supportFragmentManager
            ft = fm!!.beginTransaction()
            ft!!.replace(R.id.maindisplay, fragment!!)
            ft!!.commit()
        })
        btn2?.setOnClickListener(View.OnClickListener {
            fragment = DonerSearch()
            fm = supportFragmentManager
            ft = fm!!.beginTransaction()
            ft!!.replace(R.id.maindisplay, fragment!!)
            ft!!.commit()
        })
        btn3?.setOnClickListener(View.OnClickListener {
            fragment = DonerLocation()
            fm = supportFragmentManager
            ft = fm!!.beginTransaction()
            ft!!.replace(R.id.maindisplay, fragment!!)
            ft!!.commit()
        })
        btn4?.setOnClickListener(View.OnClickListener {
            fragment = NotificationPage()
            fm = supportFragmentManager
            ft = fm!!.beginTransaction()
            ft!!.replace(R.id.maindisplay, fragment!!)
            ft!!.commit()
        })
        btn5?.setOnClickListener(View.OnClickListener {
            fragment = ProfilePage()
            fm = supportFragmentManager
            ft = fm!!.beginTransaction()
            ft!!.replace(R.id.maindisplay, fragment!!)
            ft!!.commit()
        })


    }
}