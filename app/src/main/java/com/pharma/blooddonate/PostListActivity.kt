package com.pharma.blooddonate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.type.PostalAddress
import com.pharma.blooddonate.Adapters.PostAdapter
import com.pharma.blooddonate.Models.PostModel

class PostListActivity : AppCompatActivity() {

    lateinit var postlist: RecyclerView
    lateinit var firebaseFirestore: FirebaseFirestore
    lateinit var arrayList: ArrayList<PostModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        postlist = findViewById(R.id.postlist)
        firebaseFirestore = FirebaseFirestore.getInstance()
        arrayList = arrayListOf<PostModel>()

        postlist.layoutManager = LinearLayoutManager(this)


      val model = PostModel("", "", "", "", "");
        arrayList.add(model)

        val model2 = PostModel("", "", "", "", "");
        arrayList.add(model2)

        val model3 = PostModel("", "", "", "", "");
        arrayList.add(model3)

        postlist.adapter = PostAdapter(this, arrayList)

    }


}