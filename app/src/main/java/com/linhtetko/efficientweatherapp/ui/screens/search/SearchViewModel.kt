package com.linhtetko.efficientweatherapp.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.linhtetko.efficientweatherapp.domain.vos.WeatherCardVO
import com.linhtetko.efficientweatherapp.ui.screens.base.BaseState
import com.linhtetko.efficientweatherapp.ui.screens.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchViewModel : BaseViewModel() {

    var keyword by mutableStateOf("")

    var state by mutableStateOf(BaseState<SearchScreenState>())
        private set

    fun search() {
        if (keyword.isNotEmpty() && keyword.isNotBlank()) {
            state = state.toLoadingState()
            viewModelScope.launch {
                delay(500)
                state = state.toDataState(data = SearchScreenState(WeatherCardVO.dummy))
//                state = state.toErrorState(message = "'$keyword' City cannot be found")
            }
        }
    }

    fun onKeywordChange(value: String) {
        keyword = value
    }
}