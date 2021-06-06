package com.accenture.marvel.data.pagination

import androidx.paging.DataSource
import com.accenture.marvel.model.Character

class DataSourceFactory : DataSource.Factory<Int, Character>() {
    override fun create(): CharacterDataSource = CharacterDataSource()
}