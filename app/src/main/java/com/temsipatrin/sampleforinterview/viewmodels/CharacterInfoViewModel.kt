package com.temsipatrin.sampleforinterview.viewmodels

import androidx.annotation.StringRes
import com.temsipatrin.sampleforinterview.domain.usecases.GetCharactersUseCase
import com.temsipatrin.sampleforinterview.ui.mappers.toPresentation
import com.temsipatrin.sampleforinterview.ui.models.CharacterUi
import com.temsipatrin.sampleforinterview.utils.ExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect

class CharacterInfoViewModel(
    characterId: Int,
    private val getCharactersUseCase: GetCharactersUseCase
) : BaseViewModel() {

    private var viewModelJob: Job? = null

    private val _state = MutableStateFlow<State>(State.ShowLoading)
    val state: StateFlow<State> = _state.asStateFlow()

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _state.value = State.ShowError(message)
    }

    init {
        fetchData(characterId)
    }

    private fun fetchData(characterId: Int) {
        _state.value = State.ShowLoading
        viewModelJob = launchCoroutine {
            getCharactersUseCase.execute(characterId).collect {
                _state.value = State.CharacterInfoLoaded(it.toPresentation())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob?.cancel()
    }

    sealed class State {
        data class CharacterInfoLoaded(val data: CharacterUi) : State()
        object ShowLoading : State()
        data class ShowError(@StringRes val stringId: Int) : State()
    }
}