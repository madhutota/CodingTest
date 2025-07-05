package com.madhu.codingtest.data.repository

import com.madhu.codingtest.data.mapper.toDomainModel
import com.madhu.codingtest.data.remote.RemoteApi
import com.madhu.codingtest.data.remote.model.RemoteCharacter
import com.madhu.codingtest.data.remote.model.RemoteCharacterPage
import com.madhu.codingtest.domain.repository.RemoteRepo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class RemoteRepoImplTest {

    private lateinit var api: RemoteApi
    private lateinit var repo: RemoteRepo
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        api = mock(RemoteApi::class.java)
        repo = RemoteRepoImpl(api)
    }

    @Test
    fun test_success() = runTest(testDispatcher) {

        val remoteCharacter = RemoteCharacter(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            species = "",
            gender = "",
            type = "",
            origin = RemoteCharacter.Origin(name = "", url = ""),
            location = RemoteCharacter.Location(name = "", url = ""),
            episode = emptyList(),
            url = "",
            created = ""
        )

        val remoteResponse = RemoteCharacterPage(
            info = RemoteCharacterPage.Info(
                count = 826,
                pages = 42,
                next = "https://rickandmortyapi.com/api/character?page=2",
                prev = null
            ),
            results = listOf(remoteCharacter)
        )
        val characterPage = remoteResponse.toDomainModel()

        `when`(api.fetchCharacters()).thenReturn(remoteResponse)
        val result = repo.geCharactersData().first()

        assertEquals(characterPage, result)
        verify(api).fetchCharacters()
    }
    @Test
    fun test_failure() = runTest(testDispatcher) {
        val exception = RuntimeException("API failed")

        `when`(api.fetchCharacters()).thenThrow(exception)

        try {
            repo.geCharactersData().first()
            fail("Expected RuntimeException but none was thrown")
        } catch (e: RuntimeException) {
            assertEquals("API failed", e.message)
        }

        verify(api).fetchCharacters()
    }

}