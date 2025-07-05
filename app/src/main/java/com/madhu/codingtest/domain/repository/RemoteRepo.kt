package com.madhu.codingtest.domain.repository
import com.madhu.codingtest.domain.model.CharacterPage
import kotlinx.coroutines.flow.Flow


interface RemoteRepo {
     fun geCharactersData() : Flow<CharacterPage>
}