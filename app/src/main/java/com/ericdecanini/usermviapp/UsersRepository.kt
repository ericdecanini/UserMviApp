package com.ericdecanini.usermviapp

class UsersRepository {

    private val apiService = UsersApiService()

    suspend fun loadUsers(): List<User> {
        return apiService.loadUsers()
    }
}