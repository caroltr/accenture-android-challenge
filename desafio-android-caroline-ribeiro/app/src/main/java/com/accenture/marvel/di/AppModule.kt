package com.accenture.marvel.di

import com.accenture.marvel.presentation.main.MainAdapter
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideMainAdapter(): MainAdapter = MainAdapter()
}