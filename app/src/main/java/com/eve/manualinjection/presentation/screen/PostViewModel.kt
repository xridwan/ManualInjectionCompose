package com.eve.manualinjection.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eve.manualinjection.presentation.screen.component.UiEvent
import com.eve.manualinjection.presentation.screen.component.UiState
import com.eve.manualinjection.data.local.PostEntity
import com.eve.manualinjection.domain.PostUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel(
    private val postUseCase: PostUseCase,
) : ViewModel() {

    private val _postState = MutableStateFlow<UiState<List<PostEntity>>>(UiState.Loading)
    var postState: StateFlow<UiState<List<PostEntity>>> = _postState.asStateFlow()

    init {
        fetchPost()
    }

    fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.Refresh -> {
                fetchPost()
            }
        }
    }

    private fun fetchPost() {
        viewModelScope.launch {
            postUseCase.getPostList().collect {
                _postState.value = it
            }
        }
    }
}