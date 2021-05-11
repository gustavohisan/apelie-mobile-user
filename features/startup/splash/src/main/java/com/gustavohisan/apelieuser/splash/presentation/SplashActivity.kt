package com.gustavohisan.apelieuser.splash.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gustavohisan.apelieuser.splash.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * Splash screen activity.
 */
internal class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    private val isLoginValidObserver = Observer<Boolean> { isValid ->
        if (isValid) {
            openMainScreen()
        } else {
            openLoginScreen()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash_activity)
        viewModel.getIfUserIsLoggedIn()
        viewModel.isLoginValid.observe(this, isLoginValidObserver)
    }

    private fun openMainScreen() {
        Timber.d("openMainScreen")

        startActivity(Intent(viewModel.loadMainScreenIntentAction()))
    }

    private fun openLoginScreen() {
        Timber.d("openLoginScreen")

        startActivity(Intent(viewModel.loadLoginScreenIntentAction()))
    }
}
