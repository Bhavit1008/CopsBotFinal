package com.example.newchatui.chat.awarness

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.newchatui.R
import kotlinx.android.synthetic.main.activity_women_safety.*

class WomenSafety : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_women_safety)
        chipEmergencyWomen.setOnClickListener {
            val u: Uri = Uri.parse("tel:" + "1091")
            val i = Intent(Intent.ACTION_DIAL, u)
            try {
                // Launch the Phone app's dialer with a phone
                // number to dial a call.
                startActivity(i)
            } catch (s: SecurityException) {
                // show() method display the toast with
                // exception message.
                Toast.makeText(this, s.message, Toast.LENGTH_LONG)
                    .show()
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.fade_in,
            R.anim.fade_out
        )
    }
}
