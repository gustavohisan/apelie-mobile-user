package com.gustavohisan.apelieuser.splash.presentation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.gustavohisan.apelieuser.splash.R

/**
 * Splash screen activity.
 */
internal class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash_activity)
    }
}