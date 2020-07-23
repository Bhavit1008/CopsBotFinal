package com.example.newchatui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.newchatui.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var number :String = ""
    var password :String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener {
            number = edt_number_login.text.toString()
            password = edt_password_login.text.toString()
            if(number!="" && password!=""){

            }
            else
                Toast.makeText(applicationContext,"all fields are mendatory",Toast.LENGTH_SHORT).show()
        }
    }
}
