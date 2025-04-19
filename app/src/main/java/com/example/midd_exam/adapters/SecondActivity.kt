package com.example.midd_exam.adapters

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.midd_exam.R
import com.squareup.picasso.Picasso

class SecondActivity : AppCompatActivity() {

    private lateinit var imageView2: ImageView
    private lateinit var textView5: TextView
    private lateinit var textView6: TextView
    private lateinit var textView7: TextView
    private lateinit var textView8: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        imageView2 = findViewById(R.id.imageView2)
        textView5 = findViewById(R.id.textView5)
        textView6 = findViewById(R.id.textView6)
        textView7 = findViewById(R.id.textView7)
        textView8 = findViewById(R.id.textView8)



        val cityName = intent.getStringExtra("CITYNAME")
        val cityImage = intent.getStringExtra("CITYIMAGE")
        val cityDescription = intent.getStringExtra("DESCRIPTION")
        val website = intent.getStringExtra("WEBSITE")
        val location = intent.getStringExtra("LOCATION")


        textView5.text = cityName ?: "დასახელება ვერ მოიძებნა"
        textView6.text = "ლოკაცია-->> ${location ?: "ლოკაცია ვერ მოიძებნა"}"
        textView7.text = "ვებსაიტი-->> ${website?: " ვებსაიტი ვერ მოიძებნა"}"
        textView8.text = cityDescription ?: "აღწერა არ არის"


        if (!cityImage.isNullOrEmpty()) {
            Picasso.get().load(cityImage).into(imageView2)
        }
    }
}
