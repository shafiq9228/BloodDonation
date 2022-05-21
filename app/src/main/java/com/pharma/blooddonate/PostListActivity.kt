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

        getposts()


    }
    fun getposts(){
        firebaseFirestore.collection("posts")
            .get()
            .addOnCompleteListener(OnCompleteListener {task ->
                if (task.isSuccessful){
                    for (ds: DocumentSnapshot in task.result){

                        val posts = PostModel(ds.getString("name").toString(), ds.getString("title").toString(),
                        ds.getString("desc").toString(), ds.getString("postid").toString(),
                        ds.getString("url").toString())

                       arrayList.add(posts)

                    }

                    val myadapter = PostAdapter(this, arrayList)
                    postlist.adapter = myadapter

                }
                else{
                    Toast.makeText(applicationContext, "failed "+task.exception, Toast.LENGTH_SHORT).show()
                }

            })
    }

}