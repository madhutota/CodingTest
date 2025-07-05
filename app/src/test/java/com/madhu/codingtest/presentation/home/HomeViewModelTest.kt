package com.madhu.codingtest.presentation.home

import app.cash.turbine.test
import com.madhu.codingtest.domain.model.Character
import com.madhu.codingtest.domain.model.CharacterPage
import com.madhu.codingtest.domain.model.CharacterStatus
import com.madhu.codingtest.domain.repository.RemoteRepo
import com.madhu.codingtest.presentation.dispatchers.TestDispatcherProvider
import com.madhu.codingtest.util.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var remoteRepo: RemoteRepo


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        remoteRepo = mock(RemoteRepo::class.java)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchCharactersApi emits Loading then Success`() = runTest {
        val characterPage = CharacterPage(
            listOf(
                Character(
                    id = 1,
                    name = "Rick Sanchez",
                    status = CharacterStatus.Alive,
                    imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
                )
            )
        )

        `when`(remoteRepo.geCharactersData()).thenReturn(flowOf(characterPage))
        viewModel = HomeViewModel(remoteRepo, TestDispatcherProvider(testDispatcher))

        viewModel.charactersData.test {
            viewModel.fetchCharactersApi()
            advanceUntilIdle()

            assertEquals(UIState.Loading, awaitItem())
            assertEquals(UIState.Success(characterPage), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }

        verify(remoteRepo).geCharactersData()
    }

    @Test
    fun `fetchCharactersApi emits Loading then Failure`() = runTest {
        val exception = RuntimeException("Something went wrong")
        `when`(remoteRepo.geCharactersData()).thenReturn(flow { throw exception })
        viewModel = HomeViewModel(remoteRepo, TestDispatcherProvider(testDispatcher))

        viewModel.charactersData.test {
            viewModel.fetchCharactersApi()
            advanceUntilIdle()

            assertEquals(UIState.Loading, awaitItem())
            val failure = awaitItem()
            assertTrue(failure is UIState.Failure)
            assertEquals("Something went wrong", (failure as UIState.Failure).throwable?.message)
            cancelAndIgnoreRemainingEvents()
        }

        verify(remoteRepo).geCharactersData()
    }

}