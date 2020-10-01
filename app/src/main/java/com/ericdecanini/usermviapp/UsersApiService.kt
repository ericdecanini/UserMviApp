package com.ericdecanini.usermviapp

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersApiService {

    val api = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
        .create(UsersApi::class.java)

    suspend fun loadUsers(): List<User> {
        return api.loadUserList(10).results
    }

}
