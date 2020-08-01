package com.example.newchatui.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.custom.sliderimage.logic.SliderImage
import com.example.newchatui.R
import com.example.newchatui.chat.IntroActivity
import com.example.newchatui.features.ReportIncidentActivity
import com.example.newchatui.features.alerts.AlertsActivity
import com.example.newchatui.features.awarnessPages.TwitterActivity
import com.example.newchatui.features.statusTracking.StatusTrackingActivity
import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.activity_twitter.*

class DashBoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        cardChatbot.setOnClickListener{
            val intent = Intent(this,IntroActivity::class.java)
            startActivity(intent)
        }

        cardAlerts.setOnClickListener {
            val intent = Intent(this,AlertsActivity::class.java)
            startActivity(intent)
        }

        cardNearbyPoliceStation.setOnClickListener {

        }

        cardReportIncident.setOnClickListener {
            val intent = Intent(this,ReportIncidentActivity::class.java)
            startActivity(intent)
        }

        cardStatustracking.setOnClickListener {
            val intent = Intent(this,StatusTrackingActivity::class.java)
            startActivity(intent)
        }

        cardTwitterFeeds.setOnClickListener {
            val intent = Intent(this,TwitterActivity::class.java)
            startActivity(intent)
        }

        chipSOS.setOnClickListener {
            Toast.makeText(this,"Don't worry your location has been shared with authority",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
