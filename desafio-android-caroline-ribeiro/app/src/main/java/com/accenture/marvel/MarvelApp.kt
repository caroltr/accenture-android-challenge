package com.accenture.marvel

import android.app.Application
import com.accenture.marvel.di.DaggerAppComponent
import com.accenture.marvel.di.RepositoryModule

class MarvelApp : Application() {

    val appComponent = DaggerAppComponent.builder()
        .repositoryModule(RepositoryModule(this))
        .build()
}