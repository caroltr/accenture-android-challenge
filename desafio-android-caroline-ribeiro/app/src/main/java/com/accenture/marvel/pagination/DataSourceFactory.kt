package com.accenture.marvel.pagination

import androidx.paging.DataSource
import com.accenture.marvel.network.model.CharacterResponse

class DataSourceFactory : DataSource.Factory<Int, CharacterResponse>() {
    override fun create(): CharacterDataSource = CharacterDataSource()
}