package com.example.newchatui.features

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newchatui.R
import com.example.newchatui.api.RetrofitClient
import com.example.newchatui.model.ComplaintResponse
import com.example.newchatui.model.ImageResponse
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_report_incident.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream


class ReportIncidentActivity : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 1
    private var filePath: Uri? = null
    private var firebaseStorage: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var storage = FirebaseStorage.getInstance()
    var url :String = ""
    var desc :String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_incident)
        dispatchTakePictureIntent()
        btnIncidentRetake.setOnClickListener {
            dispatchTakePictureIntent()
        }



        btnIncidentSend.setOnClickListener {
            desc = edtIncidentDesc.text.toString()
            if(desc != ""){
                uploadIncident(desc)
            }
            else{
                Toast.makeText(applicationContext,"Please enter crime description",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                val imageBitmap = data?.extras?.get("data") as Bitmap
                imageView.setImageBitmap(imageBitmap)
                val storageRef: StorageReference =
                    storage.getReferenceFromUrl("gs://newchatui.appspot.com")
                var time = System.nanoTime().toString()
                var filename = "images/" + time+".jpg"
                val mountainsRef = storageRef.child(time+".jpg")
                val mountainImagesRef = storageRef.child(filename)
                mountainsRef.getName().equals(mountainImagesRef.getName());    // true
                mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false
                imageView.isDrawingCacheEnabled = true
                imageView.buildDrawingCache()
                val bitmap = (imageView.drawable as BitmapDrawable).bitmap
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()

                var uploadTask = mountainsRef.putBytes(data)
                uploadTask.addOnFailureListener {
                    Toast.makeText(applicationContext,"not",Toast.LENGTH_SHORT).show()
                }.addOnSuccessListener {
                    Toast.makeText(applicationContext,"uploaded",Toast.LENGTH_SHORT).show()
                    mountainsRef.downloadUrl.addOnCompleteListener {
                        url = it.result.toString()
                        Toast.makeText(applicationContext,it.result.toString(),Toast.LENGTH_SHORT).show()

                    }
                }




            }
        }

    private fun uploadIncident(desc:String) {
        RetrofitClient.instance.postImage(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjoie1wiX2lkXCI6XCI1ZjBhOWIyYzEwODJkNTM5YmM3YWE0YjdcIixcIm5hbWVcIjpcIkJoYXZpdCBLYW50aGFsaWFcIixcInBob25lTnVtYmVyXCI6NzU5NzkxNzAwNyxcIklNRUlcIjoxNjQ2MTU0LFwibGF0aXR1ZGVcIjoyMy40MzI1MjEsXCJsb25naXR1ZGVcIjo3My40MzI1MTUsXCJwYXNzd29yZFwiOlwiJDJhJDEwJGZ1WTJRODZTUjQwODQzVTZ5ZHVRbk9MNGtzT3lNT2NDRzhLVjAzd2hJM1cwZVUyNTNHQkZ1XCIsXCJjcmVhdGVkQXRcIjpcIjIwMjAtMDctMTJUMDU6MTA6MDQuMjI3WlwiLFwidXBkYXRlZEF0XCI6XCIyMDIwLTA3LTEyVDA1OjEwOjA0LjIyN1pcIixcIl9fdlwiOjB9IiwiaWF0IjoxNTk0NTMwNjUwfQ.2L12RwLY8d3l962WqWiGpBuJ2Qc14nVB1bDXD0uD23E",
            url,
            desc,
            "24.48856",
            "75.258412"
        ).enqueue(object : Callback<ImageResponse> {
            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(
                call: Call<ImageResponse>,
                response: Response<ImageResponse>
            ) {
                Toast.makeText(
                    applicationContext,
                    response.message().toString(),
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("api response","Complaint successfully registered")
            }

        })

    }


}
