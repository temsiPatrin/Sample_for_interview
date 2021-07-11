package com.temsipatrin.sampleforinterview.viewmodels

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.temsipatrin.sampleforinterview.domain.usecases.GetCharactersUseCase
import com.temsipatrin.sampleforinterview.ui.mappers.toPresentation
import com.temsipatrin.sampleforinterview.ui.models.CharacterUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharacterInfoViewModel(
    characterId: Int,
    private val getCharactersUseCase: GetCharactersUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow<State>(State.ShowLoading)
    val state: StateFlow<State> = _state.asStateFlow()

    init {
        fetchData(characterId)
    }

    private fun fetchData(characterId: Int) {
        _state.value = State.ShowLoading
        viewModelScope.launch(handlerException()) {
            getCharactersUseCase.execute(characterId).collect {
                _state.value = State.CharacterInfoLoaded(it.toPresentation())
            }
        }
    }

    override fun handleError(message: Int) {
        _state.value = State.ShowError(message)
    }

    sealed class State {
        data class CharacterInfoLoaded(val data: CharacterUi) : State()
        object ShowLoading : State()
        data class ShowError(@StringRes val stringId: Int) : State()
    }
}