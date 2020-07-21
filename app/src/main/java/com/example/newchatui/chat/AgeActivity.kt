package com.example.newchatui.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.newchatui.R
import kotlinx.android.synthetic.main.activity_age.*

class AgeActivity : AppCompatActivity() {
    var age:String = ""
    var sharedName : String = "sharedPreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age)
        progress_bar_age.animateProgress(2000,10,20)

        btnAge.setOnClickListener {
            age = edtAge.text.toString()
            if(age!=""){
                val sharedPreferences = applicationContext!!.getSharedPreferences(sharedName,0)
                val editor = sharedPreferences.edit()
                editor.putString("age",age)
                editor.commit()
                var i = Intent(applicationContext, GenderActivity::class.java)
                startActivity(i)
                overridePendingTransition(
                    R.anim.fade_in,
                    R.anim.fade_out
                )
            }
            else{
                Toast.makeText(applicationContext,"please select some category",Toast.LENGTH_SHORT)
            }

       }
    }
}
