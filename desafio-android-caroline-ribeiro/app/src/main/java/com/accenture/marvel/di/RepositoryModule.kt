package com.accenture.marvel.di

import android.app.Application
import com.accenture.marvel.data.network.ApiFactory
import com.accenture.marvel.data.repository.RemoteRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule(private val application: Application) {

    @Provides
    fun api(): ApiFactory = ApiFactory(application)

    @Provides
    fun repository(): RemoteRepository = RemoteRepository(api())
}