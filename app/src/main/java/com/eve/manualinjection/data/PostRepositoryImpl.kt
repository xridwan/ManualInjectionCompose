package com.eve.manualinjection.data

import com.eve.manualinjection.data.local.PostDao
import com.eve.manualinjection.presentation.screen.component.UiState
import com.eve.manualinjection.data.remote.ApiService
import com.eve.manualinjection.data.local.PostEntity
import com.eve.manualinjection.domain.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PostRepositoryImpl(
    private val apiService: ApiService,
    private val postDao: PostDao,
) : PostRepository {

    override suspend fun getPostList(): Flow<UiState<List<PostEntity>>> {
        return flow {
            emit(UiState.Loading)
            val localPost = postDao.getAllPosts().firstOrNull()
            if (!localPost.isNullOrEmpty()) {
                emit(UiState.Success(localPost))
            } else {
                try {
                    val remotePost = apiService.getPosts()
                    val postEntities = remotePost.map {
                        PostEntity(it.id, it.title, it.body)
                    }
                    emit(UiState.Success(postEntities))
                } catch (e: Exception) {
                    emit(UiState.Failure("Error fetching data"))
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}