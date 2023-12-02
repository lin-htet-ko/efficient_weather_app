package com.linhtetko.efficientweatherapp.ui.screens

sealed class Screen(
    val route: String
) {

    companion object Route{
        const val HOME = "/home"
        const val SEARCH = "/search"
    }

    data object Home: Screen(route = HOME)

    data object Search: Screen(route = SEARCH)
}