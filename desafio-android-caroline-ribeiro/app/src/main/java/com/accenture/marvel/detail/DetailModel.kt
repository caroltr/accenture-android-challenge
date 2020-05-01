package com.accenture.marvel.detail

import com.accenture.marvel.network.model.*

data class DetailModel (
    val name: String,
    val avatarUrl: String,
    val description: String,
    val comics: List<Item>,
    val series: List<Item>,
    val stories: List<StoryItem>
)