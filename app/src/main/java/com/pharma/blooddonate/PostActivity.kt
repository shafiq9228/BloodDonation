package com.pharma.blooddonate

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import java.util.*


class PostActivity : AppCompatActivity() {

    lateinit var imgview: ImageView
    lateinit var titleet: EditText
    lateinit var descet: EditText
    lateinit var postbtn: Button
    lateinit var opncamera: LinearLayout
    lateinit var storageReference: StorageReference
    var filepath: Uri? = null
    lateinit var storage: FirebaseStorage
    lateinit var firebaseFirestore: FirebaseFirestore
    lateinit var pd: ProgressDialog
    lateinit var firebaseAuth: FirebaseAuth

    lateinit var title: String
    lateinit var desc: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)


        pd = ProgressDialog(this)
        pd.setTitle("Please Wait")
        pd.setCancelable(false)

        titleet = findViewById(R.id.titleet)


        descet = findViewById(R.id.descet)

        postbtn = findViewById(R.id.postbtn)


        storage = Firebase.storage
        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()

        opncamera = findViewById(R.id.opncamera)
        imgview = findViewById(R.id.imgview)
        opncamera.setOnClickListener(View.OnClickListener {
          //  val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(
                    Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                    1);


        })


        postbtn.setOnClickListener(View.OnClickListener {

            title = titleet.text.toString()
            desc = descet.text.toString()
            if (title.isEmpty() || desc.isEmpty()){
                Toast.makeText(applicationContext, "Please Fill Title and Description", Toast.LENGTH_SHORT).show()
            }else{
                if (filepath != null){

                   sendtostorage()

                }
                else {

                    addtodb(title, desc, "")

                }
            }


        })

    }

    private fun sendtostorage() {

        pd.show()
        storageReference = storage.getReference();

        val ref = storageReference
            .child(
                "images/"
                        + UUID.randomUUID().toString()
            )


        var uploadTask = ref.putFile(filepath!!)


        val urlTask = uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            ref.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {

                val downloadUri = task.result
                Toast.makeText(applicationContext,""+ downloadUri, Toast.LENGTH_SHORT).show()


                addtodb(title, desc, downloadUri.toString())

            } else {
                pd.dismiss()
                Toast.makeText(applicationContext, "failed "+task.exception, Toast.LENGTH_SHORT).show()
            }
        }


    }


   fun addtodb(title: String, desc: String, url: String){

       val alphabet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

       val randomString: String = List(20) { alphabet.random() }.joinToString("")


       val post = hashMapOf(

           "uuid" to firebaseAuth.currentUser?.uid,
           "title" to title,
           "desc" to desc,
           "url" to url,
           "createAt" to FieldValue.serverTimestamp(),
           "name" to  firebaseAuth.currentUser?.displayName,
           "postid" to randomString

       )

       firebaseFirestore.collection("posts")
           .add(post)
           .addOnCompleteListener(OnCompleteListener { task ->
               if (task.isSuccessful){
                   pd.dismiss()
                   Toast.makeText(applicationContext,"saved to firebase", Toast.LENGTH_SHORT).show()
                   titleet.setText("")
                   descet.setText("")
                   filepath = null
                  imgview.setImageResource(R.drawable.ic_launcher_background)


               }
               else
               {
                   Toast.makeText(applicationContext,"failed "+task.exception, Toast.LENGTH_SHORT).show()
               }
           })

   }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data?.data != null){
            filepath =  data?.data!!
            val bitmap = MediaStore.Images.Media
                .getBitmap(
                    contentResolver,
                    filepath
                )
            imgview.setImageBitmap(bitmap)
        }


    }
}