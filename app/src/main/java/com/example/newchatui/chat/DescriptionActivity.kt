package com.example.newchatui.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.newchatui.R
import kotlinx.android.synthetic.main.activity_age.*
import kotlinx.android.synthetic.main.activity_description.*
import kotlinx.android.synthetic.main.activity_description.botLogo

class DescriptionActivity : AppCompatActivity() {

    var sharedName : String = "sharedPreference"
    var description:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)


        progress_bar_description.animateProgress(2000,40,50)
        Glide.with(this).asGif().load(R.raw.logo).into(botLogo)

        btnDescription.setOnClickListener {
            description = edtDescription.text.toString()
            if(description != null){
                val sharedPreferences = applicationContext!!.getSharedPreferences(sharedName,0)
                val editor = sharedPreferences.edit()
                editor.putString("description",description)
                editor.commit()
                var i = Intent(applicationContext, DateLocActivity::class.java)
                startActivity(i)
                overridePendingTransition(
                    R.anim.fade_in,
                    R.anim.fade_out
                )
            }

            else{
                Toast.makeText(applicationContext,"please enter the crime description",Toast.LENGTH_SHORT)
            }

        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }
}
