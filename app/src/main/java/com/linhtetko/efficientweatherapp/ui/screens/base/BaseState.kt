package com.linhtetko.efficientweatherapp.ui.screens.base

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable

data class BaseState<T>(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: T? = null
) {

    companion object{

    }

    fun toLoadingState() = copy(isLoading = true, error = "")

    infix fun toErrorState(message: String) = copy(isLoading = false, error = message)

    infix fun toDataState(data: T) = copy(isLoading = false, error = "", data = data)
}

@Composable
fun <T> UiStateMapper(
    state: BaseState<T>,
    loadingUi: @Composable () -> Unit,
    errorUi: @Composable (String) -> Unit,
    contentUi: @Composable (T) -> Unit
) {
    AnimatedContent(targetState = state, label = "Ui State") {
        when {
            it.isLoading -> loadingUi()
            it.error.isNotBlank() || state.error.isNotEmpty() -> errorUi(state.error)
            it.data != null -> contentUi(it.data)
        }
    }
}