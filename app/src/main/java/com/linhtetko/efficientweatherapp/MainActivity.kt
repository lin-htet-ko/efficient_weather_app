package com.linhtetko.efficientweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.linhtetko.efficientweatherapp.ui.screens.home.HomeScreen
import com.linhtetko.efficientweatherapp.ui.screens.home.HomeViewModel
import com.linhtetko.efficientweatherapp.ui.screens.search.SearchScreen
import com.linhtetko.efficientweatherapp.ui.screens.search.SearchViewModel
import com.linhtetko.efficientweatherapp.ui.theme.EfficientWeatherAppTheme

class MainActivity : ComponentActivity() {

    private val homeViewModel by viewModels<HomeViewModel>()
    private val searchViewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel.getCurrentWeather()
        homeViewModel.getTheNext5DaysWeatherForecast()

        setContent {
            EfficientWeatherAppTheme {
                SearchScreen(viewModel = searchViewModel)
            }
        }
    }
}