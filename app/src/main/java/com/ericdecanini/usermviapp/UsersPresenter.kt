package com.ericdecanini.usermviapp

import kotlinx.coroutines.*

class UsersPresenter {

    private lateinit var view: UsersActivity
    private val scope = CoroutineScope(Job())
    private var currentState: UserState? = null

    private val usersRepository = UsersRepository()

    fun bind(view: UsersActivity) {
        this.view = view
        getUsers()
    }

    fun unbind() {
        scope.cancel()
    }

    private fun getUsers() {
        scope.launch {
            val usersList = loadUsers()
            withContext(Dispatchers.Main) {
                view.render(reduce(currentState, usersList))
            }
        }
    }

    private suspend fun loadUsers(): List<User> {
        return usersRepository.loadUsers()
    }

    private fun reduce(previous: UserState?, new: List<User>): UserState {
        val previousData = (previous as? UserState.DataState)?.data
        val newData = previousData?.plus(new) ?: new
        return UserState.DataState(newData).also { currentState = it }
    }
}
