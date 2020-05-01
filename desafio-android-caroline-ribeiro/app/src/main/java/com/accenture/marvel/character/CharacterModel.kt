package com.accenture.marvel.character

import com.accenture.marvel.network.model.*

data class CharacterModel (
    val name: String,
    val avatarUrl: String,
    val description: String,
    val comics: List<Item>,
    val series: List<Item>,
    val stories: List<StoryItem>
)