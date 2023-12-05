package com.linhtetko.efficientweatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.linhtetko.efficientweatherapp.ui.screens.Screen
import com.linhtetko.efficientweatherapp.ui.screens.home.HomeScreen
import com.linhtetko.efficientweatherapp.ui.screens.home.HomeViewModel
import com.linhtetko.efficientweatherapp.ui.screens.search.SearchScreen
import com.linhtetko.efficientweatherapp.ui.screens.search.SearchViewModel
import com.linhtetko.efficientweatherapp.ui.theme.EfficientWeatherAppTheme
import com.linhtetko.efficientweatherapp.ui.utils.EfficientPreview
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

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
                },
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