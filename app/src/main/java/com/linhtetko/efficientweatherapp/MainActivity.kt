package com.linhtetko.efficientweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.linhtetko.efficientweatherapp.ui.screens.home.HomeScreen
import com.linhtetko.efficientweatherapp.ui.screens.home.HomeViewModel
import com.linhtetko.efficientweatherapp.ui.theme.EfficientWeatherAppTheme

class MainActivity : ComponentActivity() {

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel.getCurrentWeather()
        homeViewModel.getTheNext5DaysWeatherForecast()

        setContent {
            EfficientWeatherAppTheme {
                HomeScreen(viewModel = homeViewModel)
            }
        }
    }
}