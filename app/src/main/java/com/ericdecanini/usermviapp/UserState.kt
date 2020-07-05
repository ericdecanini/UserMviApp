package com.ericdecanini.usermviapp

sealed class UserState {
    object LoadingState: UserState()
    data class DataState(val data: List<User>): UserState()
    data class ErrorState(val error: String): UserState()
}