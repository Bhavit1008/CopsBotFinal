package com.example.newchatui.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newchatui.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var name : String = ""

    var vis :Boolean = true

    var sharedName : String = "sharedPreference"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            progress_bar_1.animateProgress(2000,0,10)
            btnName.setOnClickListener {
            name = edtName.text.toString()
                if(name!=null){
                    val sharedPreferences = applicationContext!!.getSharedPreferences(sharedName,0)
                    val editor = sharedPreferences.edit()
                    editor.putString("name",name)
                    editor.commit()
                    var i = Intent(applicationContext, AgeActivity::class.java)
                    startActivity(i)
                    overridePendingTransition(
                        R.anim.fade_in,
                        R.anim.fade_out
                    )
                }
        }
    }
}
