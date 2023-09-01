package com.eve.manualinjection.presentation.screen.component

sealed class UiState<out T>{
    object Loading: UiState<Nothing>()
    data class Success<T>(val data: T): UiState<T>()
    data class Failure(val message: String): UiState<Nothing>()
}
