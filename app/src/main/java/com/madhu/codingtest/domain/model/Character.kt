package com.madhu.codingtest.domain.model

data class Character(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val status: CharacterStatus,
)