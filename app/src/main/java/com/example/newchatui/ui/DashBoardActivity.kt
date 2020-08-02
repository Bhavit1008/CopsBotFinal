package com.example.newchatui.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.newchatui.R
import com.example.newchatui.ui.chat.IntroActivity
import com.example.newchatui.features.ReportIncidentActivity
import com.example.newchatui.features.alerts.AlertsActivity
import com.example.newchatui.features.awarnessPages.TwitterActivity
import com.example.newchatui.features.statusTracking.StatusTrackingActivity
import kotlinx.android.synthetic.main.activity_dash_board.*
import java.util.*

@Suppress("DEPRECATED")
class DashBoardActivity : AppCompatActivity() {
    var language : String = ""
    var sharedName : String = "sharedPreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences = applicationContext!!.getSharedPreferences(sharedName, Context.MODE_PRIVATE)
        if(sharedPreferences!=null){
            language = sharedPreferences.getString("language", "name")
            if(language == "Hindi")
                language = "hi"
            if(language == "English")
                language = "en"
        }
        Toast.makeText(applicationContext,language,Toast.LENGTH_SHORT).show()
        setLocale(language)
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
            sendSMS()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    fun setLocale(lang: String?) {
        val myLocale = Locale(lang)
        val res = resources
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, res.displayMetrics)
    }


    fun sendSMS()
    {
        val uri = Uri.parse("smsto:7726062540")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra("sms_body", "SOS  Message")
        startActivity(intent)
    }
}
