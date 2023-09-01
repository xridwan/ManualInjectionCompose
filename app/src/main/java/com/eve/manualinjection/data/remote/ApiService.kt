package com.eve.manualinjection.data.remote

import retrofit2.http.GET


interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<PostResponse>
}