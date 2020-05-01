package com.accenture.marvel.pagination

import androidx.paging.PageKeyedDataSource
import com.accenture.marvel.network.model.CharacterResponse

class CharacterDataSource : PageKeyedDataSource<Int, CharacterResponse>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CharacterResponse>
    ) {

    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterResponse>
    ) {

    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterResponse>
    ) {

    }
}