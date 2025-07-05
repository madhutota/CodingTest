package com.madhu.codingtest.data.mapper

import com.madhu.codingtest.data.remote.model.RemoteCharacter
import com.madhu.codingtest.data.remote.model.RemoteCharacterPage
import com.madhu.codingtest.domain.model.CharacterPage
import com.madhu.codingtest.domain.model.Character
import com.madhu.codingtest.domain.model.CharacterStatus


fun RemoteCharacterPage.toDomainModel(): CharacterPage {
    return CharacterPage(
        characters = this.results.toDomainModelList()
    )
}

fun RemoteCharacter.toDomainModel(): Character {
    val characterStatus = when (status.lowercase()) {
        "alive" -> CharacterStatus.Alive
        "dead" -> CharacterStatus.Dead
        else -> CharacterStatus.Unknown
    }
    return Character(
        id = this.id,
        imageUrl =  this.image,
        name = this.name,
        status = characterStatus

    )
}


fun List<RemoteCharacter>.toDomainModelList(): List<Character> {
    return this.map { it.toDomainModel() }
}