package com.madhu.codingtest.data.repository

import com.madhu.codingtest.data.mapper.toDomainModel
import com.madhu.codingtest.data.remote.RemoteApi
import com.madhu.codingtest.domain.model.CharacterPage

import com.madhu.codingtest.domain.repository.RemoteRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(private val api: RemoteApi) : RemoteRepo {
    override fun geCharactersData(): Flow<CharacterPage> =
        flow { emit(api.fetchCharacters())}.map { it.toDomainModel() }
}