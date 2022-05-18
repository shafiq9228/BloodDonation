package com.pharma.blooddonate

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
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
    lateinit var filepath: Uri
    lateinit var storage: FirebaseStorage
    lateinit var firebaseFirestore: FirebaseFirestore
    lateinit var pd: ProgressDialog


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
            sendtostorage()

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


        var uploadTask = ref.putFile(filepath)


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

                val post = hashMapOf(


                    "url" to downloadUri.toString()
                )

            firebaseFirestore.collection("posts")
                .add(post)
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (task.isSuccessful){
                        pd.dismiss()
                        Toast.makeText(applicationContext,"saved to firebase", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        Toast.makeText(applicationContext,"failed "+task.exception, Toast.LENGTH_SHORT).show()
                    }
                })

            } else {
                pd.dismiss()
                Toast.makeText(applicationContext, "failed "+task.exception, Toast.LENGTH_SHORT).show()
            }
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        filepath =  data?.data!!
        val bitmap = MediaStore.Images.Media
            .getBitmap(
                contentResolver,
                filepath
            )
        imgview.setImageBitmap(bitmap)

    }
}