package com.eve.manualinjection.domain

import com.eve.manualinjection.data.local.PostEntity
import com.eve.manualinjection.presentation.screen.component.UiState
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPostList(): Flow<UiState<List<PostEntity>>>
}