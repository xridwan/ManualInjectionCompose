package com.eve.manualinjection.domain

import com.eve.manualinjection.data.local.PostEntity
import com.eve.manualinjection.presentation.screen.component.UiState
import kotlinx.coroutines.flow.Flow

class GetPostList(
    private val postRepository: PostRepository,
) {
    suspend operator fun invoke(): Flow<UiState<List<PostEntity>>> {
        return postRepository.getPostList()
    }
}

data class PostUseCase(
    val getPostList: GetPostList,
)