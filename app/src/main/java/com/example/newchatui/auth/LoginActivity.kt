package com.example.newchatui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.newchatui.R
import com.example.newchatui.ui.chat.CrimeReportActivity
import com.example.newchatui.ui.DashBoardActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var number :String = ""
    var password :String = ""
    var sharedName : String = "sharedPreference"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val languages = resources.getStringArray(R.array.Languages)

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, languages)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.selected_item) + " " +
                                "" + languages[position], Toast.LENGTH_SHORT
                    ).show()
                    val sharedPreferences = applicationContext!!.getSharedPreferences(sharedName,0)
                    val editor = sharedPreferences.edit()
                    editor.putString("language",languages[position])
                    editor.commit()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    val sharedPreferences = applicationContext!!.getSharedPreferences(sharedName,0)
                    val editor = sharedPreferences.edit()
                    editor.putString("language",languages[0])
                    editor.commit()
                }
            }
        }



        btn_login.setOnClickListener {
            number = edt_number_login.text.toString()
            password = edt_password_login.text.toString()
            if(number!="" && password!=""){
                val intent = Intent(this,DashBoardActivity::class.java)
                startActivity(intent)
                finish()
            }
            else
                Toast.makeText(applicationContext,"all fields are mendatory",Toast.LENGTH_SHORT).show()
        }


        complaintA.setOnClickListener {
            val intent = Intent(this,CrimeReportActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
