package com.ericdecanini.usermviapp

import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {

    @GET("api/")
    suspend fun loadUserList(
        @Query("results") results: Int
    ): UserResponse

}
