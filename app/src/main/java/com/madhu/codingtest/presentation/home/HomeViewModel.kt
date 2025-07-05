package com.madhu.codingtest.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madhu.codingtest.domain.model.CharacterPage
import com.madhu.codingtest.domain.repository.RemoteRepo
import com.madhu.codingtest.util.UIState
import com.madhu.codingtest.util.dispatchers.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var remoteRepo: RemoteRepo,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _charactersData = MutableStateFlow<UIState<CharacterPage>>(UIState.Loading)
    val charactersData = _charactersData.asStateFlow()

    fun fetchCharactersApi() {
        viewModelScope.launch(dispatchers.io) {
            _charactersData.emit(UIState.Loading)
            remoteRepo.geCharactersData()
                .catch { error ->
                    _charactersData.emit(UIState.Failure(error))
                }
                .collect { result ->
                    _charactersData.emit(UIState.Success(result))
                }
        }

    }


}