package com.example.newchatui.chat.awarness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newchatui.R

class CyberCrimeSafetyMeasures : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cyber_crime_safety_measures)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.fade_in,
            R.anim.fade_out
        )

    }
}
