package com.madhu.codingtest.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.madhu.codingtest.domain.model.CharacterPage
import com.madhu.codingtest.presentation.components.CharacterGridItem
import com.madhu.codingtest.presentation.components.LoadingState
import com.madhu.codingtest.util.UIState

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), modifier: Modifier) {

    LaunchedEffect(key1 = viewModel) {
        viewModel.fetchCharactersApi()
    }
    val scrollState = rememberLazyGridState()

    val crypto: UIState<CharacterPage> by viewModel.charactersData.collectAsStateWithLifecycle()

    val cryptoState by viewModel.charactersData.collectAsStateWithLifecycle()

    when (crypto) {
        is UIState.Loading -> {
            LoadingState()
        }

        is UIState.Failure -> {
            val error = (cryptoState as UIState.Failure).throwable
            Column(
                modifier = Modifier.padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Error: ${error?.message}", color = Color.Red)
            }

        }

        is UIState.Success -> {
            val characters = (cryptoState as UIState.Success).data.characters
            LazyVerticalGrid(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                state = scrollState,
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(characters, key = { it.id }) { character ->
                    Log.d("Api Data", "$character")
                    CharacterGridItem(
                        modifier = Modifier.fillMaxWidth(),
                        character = character
                    )
                }
            }
        }
    }
}