package com.gustavohisan.apelieuser

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.gustavohisan.apelieuser.design.ApelieTheme
import com.gustavohisan.apelieuser.navigation.NavGraph
import timber.log.Timber

/**
 * Application main activity.
 */
internal class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSplashScreen()
        setContent {
            ApelieTheme {
                NavGraph()
            }
        }
    }

    private fun setupSplashScreen() {
        Timber.d("setupSplashScreen")

        val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val fadeOut = ObjectAnimator.ofFloat(splashScreenView, View.ALPHA.name, 0f)
            fadeOut.duration = 1000L
            fadeOut.interpolator = AnticipateInterpolator()
            fadeOut.doOnEnd { splashScreenView.remove() }
            fadeOut.start()
        }
    }
}
