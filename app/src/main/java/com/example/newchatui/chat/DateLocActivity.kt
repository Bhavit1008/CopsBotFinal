package com.example.newchatui.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.newchatui.R
import kotlinx.android.synthetic.main.activity_date_loc.*

class DateLocActivity : AppCompatActivity() {
    var sharedName : String = "sharedPreference"
    var landmark:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_loc)

        progress_bar_location.animateProgress(2000,50,60)

        btnLocation.setOnClickListener {
            landmark = edtLocation.text.toString()
            if(landmark != ""){
                val sharedPreferences = applicationContext!!.getSharedPreferences(sharedName,0)
                val editor = sharedPreferences.edit()
                editor.putString("landmark",landmark)
                editor.commit()
                var i = Intent(applicationContext, DateTimePickerActivity::class.java)
                startActivity(i)
                overridePendingTransition(
                    R.anim.fade_in,
                    R.anim.fade_out
                )
            }
            else{
                Toast.makeText(applicationContext,"please enter location",Toast.LENGTH_SHORT)
            }
        }
    }
}
