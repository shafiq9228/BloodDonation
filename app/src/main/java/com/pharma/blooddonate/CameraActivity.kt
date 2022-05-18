package com.pharma.blooddonate

import android.R.attr
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity


class CameraActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var currentImagePath: String? = null
    lateinit var mystr: String

    private lateinit var btnOpenCamera: Button
    private lateinit var ivPhoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        btnOpenCamera = findViewById(R.id.btnOpenCamera)
        ivPhoto = findViewById(R.id.ivImage)



        btnOpenCamera.setOnClickListener {

            //intent to open camera app
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)


            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
           // mImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(mCurrentPhotoPath));
           //    mystr = data?.extras?.get("data").toString()
            val bitmap = data?.extras?.get("data") as Bitmap
            ivPhoto.setImageBitmap(bitmap)
            Toast.makeText(applicationContext, "toast "+bitmap, Toast.LENGTH_SHORT).show()
            val i = Intent(this, PostActivity::class.java)
             i.putExtra("image", mystr)
            startActivity(i)
        }
    }

    }
