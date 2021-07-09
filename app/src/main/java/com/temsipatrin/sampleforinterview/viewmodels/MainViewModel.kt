package com.temsipatrin.sampleforinterview.viewmodels

import androidx.annotation.StringRes
import com.temsipatrin.sampleforinterview.domain.usecases.GetCharactersShortUseCase
import com.temsipatrin.sampleforinterview.domain.usecases.GetPageInfoUseCase
import com.temsipatrin.sampleforinterview.ui.models.CharacterShortUi
import com.temsipatrin.sampleforinterview.utils.ExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect

class MainViewModel(
    private val getPageInfoUseCase: GetPageInfoUseCase,
    private val getCharactersShortUseCase: GetCharactersShortUseCase
) : BaseViewModel() {

    private var viewModelJob: Job? = null

    private val _state = MutableStateFlow<State>(State.ShowLoading)
    val state: StateFlow<State> = _state.asStateFlow()

    private var pageCount = 1
    private var currentlyPage = 1
    private val listCharacters = mutableListOf<CharacterShortUi>()


    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _state.value = State.ShowError(message)
    }

    init {
        fetchData()
    }

    private fun fetchData() {
        _state.value = State.ShowLoading
        viewModelJob = launchCoroutine {
             getPageInfoUseCase.execute().collect {
                pageCount = it.pages
                updateData()
            }
        }
    }

    fun updateData() {
        if (currentlyPage < pageCount) {
            _state.value = State.ShowLoading
            viewModelJob = launchCoroutine {
                getCharactersShortUseCase.execute(currentlyPage).collect {
                    listCharacters.addAll(it)
                    _state.value = State.CharactersLoaded(listCharacters)
                }
            }
            currentlyPage += 1
        }
    }


    override fun onCleared() {
        viewModelJob?.cancel()
        super.onCleared()
    }


    sealed class State {
        data class CharactersLoaded(val data: List<CharacterShortUi>) : State()
        object ShowLoading : State()
        data class ShowError(@StringRes val stringId: Int) : State()
    }
}