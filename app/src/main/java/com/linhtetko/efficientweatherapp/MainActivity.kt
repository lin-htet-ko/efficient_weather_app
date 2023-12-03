package com.linhtetko.efficientweatherapp

import android.os.Bundle
import android.util.Log.d
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.linhtetko.efficientweatherapp.ui.screens.Screen
import com.linhtetko.efficientweatherapp.ui.screens.home.HomeScreen
import com.linhtetko.efficientweatherapp.ui.screens.home.HomeViewModel
import com.linhtetko.efficientweatherapp.ui.screens.search.SearchScreen
import com.linhtetko.efficientweatherapp.ui.screens.search.SearchViewModel
import com.linhtetko.efficientweatherapp.ui.theme.EfficientWeatherAppTheme
import com.linhtetko.efficientweatherapp.ui.utils.EfficientPreview
import com.linhtetko.persistance.daos.base.WeatherDao
import com.linhtetko.persistance.entities.WeatherEntity
import com.linhtetko.persistance.manager.DatabaseManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var databaseManager: WeatherDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()
            EfficientWeatherAppTheme {
                EfficientApp(navController = navController)
            }
        }
    }
}

@Composable
fun EfficientApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {

        composable(Screen.Home.route) {
            val viewModel = hiltViewModel<HomeViewModel>()

            HomeScreen(
                viewModel = viewModel,
                onTapSearch = {
                    navController.navigate(Screen.Search.route)
                }
            )
        }

        composable(Screen.Search.route) {
            val viewModel = hiltViewModel<SearchViewModel>()
            SearchScreen(viewModel = viewModel, onTapNavigate = { navController.navigateUp() })
        }

    }
}

@EfficientPreview
@Composable
private fun EfficientAppPreview() {
    EfficientWeatherAppTheme(dynamicColor = false) {
        EfficientApp(navController = rememberNavController())
    }
}