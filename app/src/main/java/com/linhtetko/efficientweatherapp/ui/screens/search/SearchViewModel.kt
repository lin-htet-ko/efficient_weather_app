package com.linhtetko.efficientweatherapp.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.linhtetko.domain.repositories.WeatherRepository
import com.linhtetko.efficientweatherapp.ui.screens.base.BaseState
import com.linhtetko.efficientweatherapp.ui.screens.base.BaseViewModel
import com.linhtetko.efficientweatherapp.ui.vos.WeatherUiVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : BaseViewModel() {

    private var cacheKeyword: String = ""
    var keyword by mutableStateOf("")

    var state by mutableStateOf(BaseState<SearchScreenState>())
        private set

    fun search() {
        if (cacheKeyword.isNotEmpty() && cacheKeyword.isNotBlank()) {
            state = state.toLoadingState()
            viewModelScope.launch {
                weatherRepository.searchWeatherByCity(
                    city = cacheKeyword,
                    onSuccess = {
                        state = if (it != null)
                            state.toDataState(data = SearchScreenState(WeatherUiVO.fromDomain(it)))
                        else
                            state.toErrorState(message = "'$cacheKeyword' City cannot be found")
                    },
                    onFailure = {
                        state = state.toErrorState(message = "'$cacheKeyword' City cannot be found")
                    }
                )
            }
        }
    }

    fun onKeywordChange(value: String) {

        if (cacheKeyword != value)
            keyword = value

        cacheKeyword = keyword
    }
}