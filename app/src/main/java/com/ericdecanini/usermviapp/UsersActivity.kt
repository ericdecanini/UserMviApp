package com.ericdecanini.usermviapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_users.*

/**
 * An activity that displays a list of users
 */
class UsersActivity : AppCompatActivity() {

    // In a real app, you'd do this through some dependency injection like Dagger
    val presenter = UsersPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        presenter.bind(this)
    }

    fun render(state: UserState) {
        when(state) {
            is UserState.LoadingState -> renderLoadingState()
            is UserState.DataState -> renderDataState(state)
            is UserState.ErrorState -> renderErrorState(state)
        }
    }

    private fun renderLoadingState() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun renderDataState(dataState: UserState.DataState) {
        progress_bar.visibility = View.GONE
        users_list.adapter = UsersAdapter(dataState.data)
        users_list.layoutManager = LinearLayoutManager(this)
    }

    private fun renderErrorState(errorState: UserState.ErrorState) {
        progress_bar.visibility = View.GONE
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(errorState.error)
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }
}