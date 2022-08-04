package com.pharma.blooddonate

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class MyFragment : AppCompatActivity() {
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
        setContentView(R.layout.activity_my_fragment)
        maindisplay = findViewById(R.id.maindisplay)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        fragment = HomePage()
        fm = supportFragmentManager
        ft = fm!!.beginTransaction()
        ft!!.replace(R.id.maindisplay, fragment!!)
        ft!!.commit()
        btn1?.setOnClickListener(View.OnClickListener {
            fragment = HomePage()
            fm = supportFragmentManager
            ft = fm!!.beginTransaction()
            ft!!.replace(R.id.maindisplay, fragment!!)
            ft!!.commit()
        })


        btn2?.setOnClickListener(View.OnClickListener {
            fragment = SearchPage()
            fm = supportFragmentManager
            ft = fm!!.beginTransaction()
            ft!!.replace(R.id.maindisplay, fragment!!)
            ft!!.commit()
        })
        btn3?.setOnClickListener(View.OnClickListener {
            fragment = LocationPage()
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