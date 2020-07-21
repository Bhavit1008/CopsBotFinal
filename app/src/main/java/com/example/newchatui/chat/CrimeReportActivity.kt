package com.example.newchatui.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.newchatui.R
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_crime_report.*

class CrimeReportActivity : AppCompatActivity() {

    var description :String = ""
    var sharedName : String = "sharedPreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime_report)

        progress_bar_category.animateProgress(2000,30,40)

        chipGroupCrime.setOnCheckedChangeListener { group, checkedId: Int ->
            // Get the checked chip instance from chip group
            val chip: Chip? = findViewById(checkedId)

            chip?.let {
                // Show the checked chip text on toast message
                Toast.makeText(applicationContext,it.text,Toast.LENGTH_SHORT).show()
                val sharedPreferences = applicationContext!!.getSharedPreferences(sharedName,0)
                val editor = sharedPreferences.edit()
                editor.putString("category",it.toString())
                editor.commit()
                var i = Intent(applicationContext, DescriptionActivity::class.java)
                startActivity(i)
                overridePendingTransition(
                    R.anim.fade_in,
                    R.anim.fade_out
                )

            }
        }


    }
}
