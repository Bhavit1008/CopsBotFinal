package com.example.newchatui.chat

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.newchatui.R
import com.example.newchatui.api.RetrofitClient
import com.example.newchatui.model.ComplaintResponse
import kotlinx.android.synthetic.main.activity_complaint_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComplaintDetailsActivity : AppCompatActivity() {
    var sharedName: String = "sharedPreference"
    var victimName: String = ""
    var age: String = ""
    var gender: String = ""
    var category: String = ""
    var description: String = ""
    var landmark: String = ""
    var dateOfIncident: String = ""
    var iLatitude: String = ""
    var iLongitude: String = ""
    var authorization: String = ""
    var timeOfIncident:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaint_details)
        progress_bar_complaintDetails.animateProgress(2000, 90, 100)
        val sharedPreferences = applicationContext!!.getSharedPreferences(sharedName, Context.MODE_PRIVATE)
        if(sharedPreferences!=null){
            victimName = sharedPreferences.getString("name", "name")
            age = sharedPreferences.getString("age", "age")
            gender = sharedPreferences.getString("gender", "gender")
            category = sharedPreferences.getString("category", "category")
            description = sharedPreferences.getString("description", "description")
            landmark = sharedPreferences.getString("landmark", "landmark")
            dateOfIncident = sharedPreferences.getString("date", "date")
        }

        iLatitude = "23.453265"
        iLongitude = "73.432515"
        timeOfIncident= "16:25 PM"
        authorization = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjoie1wiX2lkXCI6XCI1ZjBhOWIyYzEwODJkNTM5YmM3YWE0YjdcIixcIm5hbWVcIjpcIkJoYXZpdCBLYW50aGFsaWFcIixcInBob25lTnVtYmVyXCI6NzU5NzkxNzAwNyxcIklNRUlcIjoxNjQ2MTU0LFwibGF0aXR1ZGVcIjoyMy40MzI1MjEsXCJsb25naXR1ZGVcIjo3My40MzI1MTUsXCJwYXNzd29yZFwiOlwiJDJhJDEwJGZ1WTJRODZTUjQwODQzVTZ5ZHVRbk9MNGtzT3lNT2NDRzhLVjAzd2hJM1cwZVUyNTNHQkZ1XCIsXCJjcmVhdGVkQXRcIjpcIjIwMjAtMDctMTJUMDU6MTA6MDQuMjI3WlwiLFwidXBkYXRlZEF0XCI6XCIyMDIwLTA3LTEyVDA1OjEwOjA0LjIyN1pcIixcIl9fdlwiOjB9IiwiaWF0IjoxNTk0NTMwNjUwfQ.2L12RwLY8d3l962WqWiGpBuJ2Qc14nVB1bDXD0uD23E"
        if (victimName != "" && description != "") {
            RetrofitClient.instance.createUser(
                authorization,
                victimName,
                description,
                category,
                age,
                gender,
                iLatitude,
                iLongitude,
                landmark,
                dateOfIncident,
                timeOfIncident
            ).enqueue(object : Callback<ComplaintResponse> {
                override fun onFailure(call: Call<ComplaintResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(
                    call: Call<ComplaintResponse>,
                    response: Response<ComplaintResponse>
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
}

