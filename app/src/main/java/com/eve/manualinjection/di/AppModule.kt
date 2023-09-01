package com.eve.manualinjection.di

import android.content.Context
import androidx.room.Room
import com.eve.manualinjection.data.PostRepositoryImpl
import com.eve.manualinjection.data.local.PostDao
import com.eve.manualinjection.data.local.PostDatabase
import com.eve.manualinjection.data.remote.ApiService
import com.eve.manualinjection.domain.GetPostList
import com.eve.manualinjection.domain.PostRepository
import com.eve.manualinjection.domain.PostUseCase
import com.eve.manualinjection.utils.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

interface AppModule {
    val postDatabase: PostDatabase
    val postDao: PostDao
    val apiService: ApiService
    val postRepository: PostRepository
    val postUseCase: PostUseCase
}

class AppModuleImpl(
    private val appContext: Context,
) : AppModule {

    override val postDatabase: PostDatabase by lazy {
        Room.databaseBuilder(
            appContext,
            PostDatabase::class.java,
            Utils.DB_NAME
        ).build()
    }

    override val postDao: PostDao by lazy {
        postDatabase.postDao()
    }

    override val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    override val postRepository: PostRepository by lazy {
        PostRepositoryImpl(
            apiService = apiService,
            postDao = postDao
        )
    }

    override val postUseCase: PostUseCase by lazy {
        PostUseCase(
            getPostList = GetPostList(postRepository)
        )
    }
}
