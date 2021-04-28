package com.gustavohisan.apelieuser

import android.app.Application
import com.gustavohisan.apelieuser.login.injection.loginModule
import com.gustavohisan.apelieuser.splash.injection.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Application class used for dependency injection.
 */
internal class ApelieUserApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ApelieUserApp)
            modules(splashModule, loginModule)
        }
    }
}
