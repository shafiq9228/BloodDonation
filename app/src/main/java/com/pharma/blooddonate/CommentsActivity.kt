package com.pharma.blooddonate

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.pharma.blooddonate.Adapters.CommentAdapter
import com.pharma.blooddonate.Models.CommentModel
import java.text.SimpleDateFormat


class CommentsActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseFirestore: FirebaseFirestore
    lateinit var commentlist: RecyclerView
    lateinit var commentet: EditText
    lateinit var commentbtn: ImageView
    lateinit var postid: String
    lateinit var arraylist: ArrayList<CommentModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()
        commentet = findViewById(R.id.commentet)
        commentlist = findViewById(R.id.commentlist)

        commentbtn = findViewById(R.id.sendbtn)
        postid = intent.getStringExtra("postid").toString()

        commentlist.layoutManager = LinearLayoutManager(this)

        arraylist = arrayListOf<CommentModel>()

        commentbtn.setOnClickListener(View.OnClickListener {

            if (commentet.text.toString().isEmpty()) {
                Toast.makeText(applicationContext, "please add Comment", Toast.LENGTH_SHORT).show()
            }
            else {

                sendcomment()
            }

        })


        getcomments()


    }

    private fun getcomments() {



        arraylist.clear()
        firebaseFirestore.collection("comments")
            .whereEqualTo("postid", postid)
            .addSnapshotListener { value, e ->
                if (e != null){
                    Toast.makeText(applicationContext, "failed ", Toast.LENGTH_SHORT).show()
                }
                else{


                    for (doc in value!!) {
                        val li: Long = (doc.getTimestamp("time")?.toDate()?.getTime().toString() + "").toLong()
                        val dateFormat = SimpleDateFormat("dd-MMM-yyyy")
                        val datestr = dateFormat.format(li)
//
//                        val allcomments = CommentModel(doc.getString("name").toString(),
//                            doc.getString("comment").toString(),
//                            datestr)
//                        arraylist.add(allcomments)
                    }

                    commentlist.adapter = CommentAdapter(this, arraylist)

                }


            }



//        firebaseFirestore.collection("comments")
//            .whereEqualTo("postid", postid)
//            .get()
//            .addOnCompleteListener(OnCompleteListener { task ->
//                if (task.isSuccessful){
//
//
//
//                    for (ds: DocumentSnapshot in task.result){
//                        val li: Long = (ds.getTimestamp("time")?.toDate()?.getTime().toString() + "").toLong()
//                        val dateFormat = SimpleDateFormat("dd-MMM-yyyy")
//                        val datestr = dateFormat.format(li)
//
//                        val allcomments = CommentModel(ds.getString("name").toString(),
//                        ds.getString("comment").toString(),
//                       datestr)
//                        arraylist.add(allcomments)
//                    }
//
//                    commentlist.adapter = CommentAdapter(this, arraylist)
//
//                }
//                else{
//                    Toast.makeText(applicationContext, "Failed "+task.exception, Toast.LENGTH_SHORT).show()
//                }
//            })

    }

    fun sendcomment(){

        val comments = hashMapOf(
            "comment" to commentet.text.toString(),
            "uid" to firebaseAuth.currentUser?.uid,
            "name" to firebaseAuth.currentUser?.displayName,
            "time" to FieldValue.serverTimestamp(),
            "postid" to postid

        )
        firebaseFirestore.collection("comments")
            .add(comments)
            .addOnCompleteListener(OnCompleteListener { task ->
                if (task.isSuccessful){

                    commentet.setText("")
                    Toast.makeText(applicationContext, "Commented", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(applicationContext, "Failed "+task.exception, Toast.LENGTH_SHORT).show()
                }

            })


}
}