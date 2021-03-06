package com.projects.mercadopago.app

import android.app.Application
import com.projects.mercadopago.data.repository.ProductsRepository
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MercadoPagoApp:Application() {

    /**
     * onCreate is called before the first screen is shown to the user.
     *
     * Use it to setup any background tasks, running expensive setup operations in a background
     * thread to avoid delaying app start.
     */

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}