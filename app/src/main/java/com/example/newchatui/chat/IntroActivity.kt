package com.example.newchatui.chat

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.newchatui.R
import com.example.newchatui.chat.awarness.MainAwarness
import kotlinx.android.synthetic.main.activity_intro.*
import java.util.*

@Suppress("DEPRECATED")
class IntroActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        setLocale("hi")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        Glide.with(this).asGif().load(R.raw.logo).into(botLogo)
        val handler = Handler()

        val runnable = Runnable{
            chipGroup.visibility = View.VISIBLE
            val a =
                AnimationUtils.loadAnimation(this, R.anim.view_anime)
            a.reset()
            chipGroup.clearAnimation()
            chipGroup.startAnimation(a)
        }
        handler.postDelayed(runnable,0)

        crimeRegChip.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
        crimeAwareChip.setOnClickListener {
            val intent = Intent(this,MainAwarness::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }


    fun setLocale(lang: String?) {
        val myLocale = Locale(lang)
        val res = resources
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, res.displayMetrics)
    }
}
