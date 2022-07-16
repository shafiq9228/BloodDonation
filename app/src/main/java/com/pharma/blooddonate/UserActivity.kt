package com.pharma.blooddonate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserActivity : AppCompatActivity() {
    lateinit var userlistrv: RecyclerView
    lateinit var arrayList: ArrayList<UserModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        userlistrv = findViewById(R.id.userlistrv)
        userlistrv.setLayoutManager(LinearLayoutManager(this))
        arrayList = ArrayList()

        val model = UserModel("", "", "", "", "", "")
        arrayList.add(model)

        val model2 = UserModel("", "", "", "", "", "")
        arrayList.add(model2)
        val model3 = UserModel("", "", "", "", "", "")
        arrayList.add(model3)

        userlistrv.adapter = UsersAdapter(arrayList, this)
    }
}