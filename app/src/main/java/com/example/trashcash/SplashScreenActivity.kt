package com.example.trashcash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar

class SplashScreenActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 4000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        val animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation)
        findViewById<ImageView>(R.id.ivLogoSplash).startAnimation(animation)

        Handler().postDelayed({
            findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}