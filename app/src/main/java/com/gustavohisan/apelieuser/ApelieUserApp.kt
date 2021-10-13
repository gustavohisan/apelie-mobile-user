package com.gustavohisan.apelieuser

import android.app.Application
import com.gustavohisan.apelie.user.sharedpreferences.injection.sharedPreferencesModule
import com.gustavohisan.apelieuser.api.injection.apiModule
import com.gustavohisan.apelieuser.checkin.injection.checkinModule
import com.gustavohisan.apelieuser.domain.injection.domainModule
import com.gustavohisan.apelieuser.feed.injection.feedModule
import com.gustavohisan.apelieuser.login.injection.loginModule
import com.gustavohisan.apelieuser.memory.injection.memoryModule
import com.gustavohisan.apelieuser.product.injection.productModule
import com.gustavohisan.apelieuser.register.injection.registerModule
import com.gustavohisan.apelieuser.repository.injection.repositoryModule
import com.gustavohisan.apelieuser.search.injection.searchModule
import com.gustavohisan.apelieuser.store.injection.storeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Application class used for dependency injection.
 */
internal class ApelieUserApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        startKoin {
            androidContext(this@ApelieUserApp)
            modules(
                checkinModule,
                domainModule,
                repositoryModule,
                memoryModule,
                sharedPreferencesModule,
                apiModule,
                loginModule,
                registerModule,
                feedModule,
                storeModule,
                productModule,
                searchModule
            )
        }
    }
}
