package com.temsipatrin.sampleforinterview.viewmodels

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.temsipatrin.sampleforinterview.domain.usecases.GetCharactersShortUseCase
import com.temsipatrin.sampleforinterview.domain.usecases.GetPageInfoUseCase
import com.temsipatrin.sampleforinterview.ui.mappers.toPresentationShort
import com.temsipatrin.sampleforinterview.ui.models.CharacterShortUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPageInfoUseCase: GetPageInfoUseCase,
    private val getCharactersShortUseCase: GetCharactersShortUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow<State>(State.ShowLoading)
    val state: StateFlow<State> = _state.asStateFlow()

    private var pageCount = 1
    private var currentlyPage = 1
    private val listCharacters = mutableListOf<CharacterShortUi>()

    init {
        fetchData()
    }

    private fun fetchData() {
        _state.value = State.ShowLoading
        viewModelScope.launch(handlerException()) {
            getPageInfoUseCase.execute().collect {
                pageCount = it.pages
                updateData()
            }
        }
    }

    fun updateData() {
        if (currentlyPage < pageCount) {
            _state.value = State.ShowLoading
            viewModelScope.launch(handlerException()) {
                getCharactersShortUseCase.execute(currentlyPage).collect { list ->
                    listCharacters.addAll(list.map { it.toPresentationShort() })
                    _state.value = State.CharactersLoaded(listCharacters)
                }
            }
            currentlyPage += 1
        }
    }

    sealed class State {
        data class CharactersLoaded(val data: List<CharacterShortUi>) : State()
        object ShowLoading : State()
        data class ShowError(@StringRes val stringId: Int) : State()
    }

    override fun handleError(message: Int) {
        _state.value = State.ShowError(message)
    }
}