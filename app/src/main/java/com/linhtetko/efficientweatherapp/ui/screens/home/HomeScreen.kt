package com.linhtetko.efficientweatherapp.ui.screens.home

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.linhtetko.efficientweatherapp.R
import com.linhtetko.efficientweatherapp.ui.components.DangerAlertMessage
import com.linhtetko.efficientweatherapp.ui.components.EfficientAppbar
import com.linhtetko.efficientweatherapp.ui.components.LoadingUi
import com.linhtetko.efficientweatherapp.ui.components.VerticalSpaceGeneral
import com.linhtetko.efficientweatherapp.ui.components.WeatherDescriptiveByCenterAlign
import com.linhtetko.efficientweatherapp.ui.components.WeatherGeneralStatus
import com.linhtetko.efficientweatherapp.ui.components.WeatherPredicateCard
import com.linhtetko.efficientweatherapp.ui.screens.base.BaseState
import com.linhtetko.efficientweatherapp.ui.screens.base.UiStateMapper
import com.linhtetko.efficientweatherapp.ui.theme.EfficientWeatherAppTheme
import com.linhtetko.efficientweatherapp.ui.utils.EfficientPreview
import com.linhtetko.efficientweatherapp.ui.vos.WeatherUiVO
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onTapSearch: () -> Unit,
) {

    val context = LocalContext.current

    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (it[Manifest.permission.ACCESS_FINE_LOCATION] == true && it[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
                viewModel.getCity(context = context, onSuccess = { city ->
                    viewModel.getCurrentWeather(city)
                    viewModel.getTheNext5DaysWeatherForecast(city)
                }, onFailure = {})
            }
        }

    LaunchedEffect(key1 = permissionLauncher) {
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    HomeScreen(
        modifier = modifier,
        state = viewModel.state,
        onTapSearch = onTapSearch
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeScreenState,
    onTapSearch: () -> Unit,
) {
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        HomeAppbar(
            modifier = Modifier.padding(
                dimensionResource(id = R.dimen.space_general)
            ), onTapSearch = onTapSearch
        )
    }) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = dimensionResource(id = R.dimen.space_2x)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_general))
        ) {


            item {
                CurrentWeatherSection(
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.space_general)),
                    state = state.current
                )
            }

            item {
                TheNext5DaysWeatherForecastSection(state = state.theNext5DaysForecasts)
            }

            item {
                VerticalSpaceGeneral()
            }
        }
    }
}

@EfficientPreview
@Composable
private fun HomeScreenPreview() {
    EfficientWeatherAppTheme(dynamicColor = false) {
        HomeScreen(state = HomeScreenState.dummy, onTapSearch = {})
    }
}

@Composable
private fun HomeAppbar(modifier: Modifier = Modifier, onTapSearch: () -> Unit) {
    EfficientAppbar(modifier = modifier.fillMaxWidth(),
        title = stringResource(R.string.lbl_app_title),
        actions = {
            IconButton(onClick = onTapSearch) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.lbl_search),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        })
}

@EfficientPreview
@Composable
private fun HomeAppbarPreview() {
    EfficientWeatherAppTheme(dynamicColor = false) {
        HomeAppbar(onTapSearch = {})
    }
}

@Composable
fun CurrentWeatherSection(
    modifier: Modifier = Modifier,
    location: String,
    date: String,
    temp: String,
    status: String,
    statusIcon: String,
    windySpeed: String,
    uv: String,
    cloud: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_2x))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_2x))) {
                WeatherGeneralStatus(
                    isValueRight = true, windySpeed = windySpeed, uv = uv, cloud = cloud
                )
                Text(text = date, color = MaterialTheme.colorScheme.onBackground)
            }
            WeatherDescriptiveByCenterAlign(
                statusIcon = statusIcon, status = status, temp = temp, location = location
            )
        }

        Divider(
            color = MaterialTheme.colorScheme.onBackground,
            thickness = dimensionResource(id = R.dimen.size_stroke_general)
        )
    }
}

@Composable
fun CurrentWeatherSection(
    modifier: Modifier = Modifier,
    state: BaseState<WeatherUiVO>
) {

    UiStateMapper(
        state = state,
        loadingUi = {
            LoadingUi(modifier)
        },
        errorUi = {
            DangerAlertMessage(
                visible = (it.isNotBlank() && it.isNotEmpty()),
                message = it,
                onDismiss = {}
            )
        },
        contentUi = { weather ->
            CurrentWeatherSection(
                modifier = modifier.fillMaxWidth(),
                location = weather.location,
                date = weather.date,
                temp = stringResource(id = R.string.lbl_s_degree_cel, weather.tempC),
                status = weather.status,
                statusIcon = weather.statusIcon,
                windySpeed = stringResource(id = R.string.lbl_s_kmp, weather.windySpeedMph),
                uv = weather.uv,
                cloud = stringResource(R.string.lbl_s_percentage, weather.cloud)
            )
        })
}

@EfficientPreview
@Composable
private fun CurrentWeatherSectionPreview() {
    val weather by remember {
        mutableStateOf(BaseState(data = WeatherUiVO.dummy))
    }

    EfficientWeatherAppTheme(dynamicColor = false) {
        Column {
            CurrentWeatherSection(state = weather)
        }
    }
}

@Composable
fun TheNext5DaysWeatherForecastSection(
    state: BaseState<List<WeatherUiVO>>
) {

    UiStateMapper(
        state = state,
        loadingUi = {
            LoadingUi(
                modifier = Modifier
            )
        },
        errorUi = {
            DangerAlertMessage(
                visible = (it.isNotBlank() && it.isNotEmpty()),
                message = it,
                onDismiss = {}
            )
        },
        contentUi = {
            Column {
                Text(
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.space_small)),
                    text = stringResource(R.string.lbl_five_days_forecast).uppercase(),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )
                VerticalSpaceGeneral()
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_general))
                ) {

                    it.forEach { weather ->
                        WeatherPredicateCard(weather = weather)
                    }
                }
            }
        })
}

@EfficientPreview
@Composable
private fun TheNext5DaysWeatherForecastSectionPreview() {

    val state by remember {
        mutableStateOf(BaseState(data = (0..5).map { WeatherUiVO.dummy }))
    }

    EfficientWeatherAppTheme(dynamicColor = false) {
        Column {
            TheNext5DaysWeatherForecastSection(state = state)
        }
    }
}