package com.accenture.marvel.data.pagination

import androidx.paging.DataSource
import com.accenture.marvel.data.model.Character
import com.accenture.marvel.data.repository.RemoteRepository
import javax.inject.Inject

class DataSourceFactory @Inject constructor(
    private val repository: RemoteRepository
) : DataSource.Factory<Int, Character>() {
    override fun create(): CharacterDataSource = CharacterDataSource(repository)
}
