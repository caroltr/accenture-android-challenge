package com.accenture.marvel.di

import com.accenture.marvel.presentation.character.CharacterActivity
import com.accenture.marvel.presentation.hq.HqActivity
import com.accenture.marvel.presentation.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(characterActivity: CharacterActivity)

    fun inject(hqActivity: HqActivity)
}