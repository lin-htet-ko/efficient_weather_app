package com.linhtetko.efficientweatherapp.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.linhtetko.efficientweatherapp.R
import com.linhtetko.efficientweatherapp.domain.vos.WeatherCardVO
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

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    HomeScreen(modifier = modifier, state = viewModel.state, onTapSearch = {})
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, state: HomeScreenState, onTapSearch: () -> Unit) {
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        HomeAppbar(
            modifier = Modifier.padding(
                dimensionResource(id = R.dimen.space_general)
            ), onTapSearch = onTapSearch
        )
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = dimensionResource(id = R.dimen.space_2x)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_general))
        ) {

            CurrentWeatherSection(
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.space_general)),
                state = state.current
            )

            TheNext5DaysWeatherForecastSection(state = state.theNext5DaysForecasts)

            VerticalSpaceGeneral()
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

//b58cabed85c14d8491e142059230112
@Composable
private fun HomeAppbar(modifier: Modifier = Modifier, onTapSearch: () -> Unit) {
    EfficientAppbar(
        modifier = modifier.fillMaxWidth(),
        title = stringResource(R.string.lbl_app_title),
        actions = {
            IconButton(onClick = onTapSearch) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.lbl_search),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}

@EfficientPreview
@Composable
private fun HomeAppbarPreview() {
    EfficientWeatherAppTheme(dynamicColor = false) {
        HomeAppbar(
            onTapSearch = {}
        )
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
    rain: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_2x))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_2x))) {
                WeatherGeneralStatus(
                    isValueRight = true,
                    windySpeed = windySpeed,
                    uv = uv,
                    rain = rain
                )
                Text(text = date, color = MaterialTheme.colorScheme.onBackground)
            }
            WeatherDescriptiveByCenterAlign(
                statusIcon = statusIcon,
                status = status,
                temp = temp,
                location = location
            )
        }

        Divider(
            color = MaterialTheme.colorScheme.onBackground,
            thickness = dimensionResource(id = R.dimen.size_stroke_general)
        )
    }
}

@Composable
fun CurrentWeatherSection(modifier: Modifier = Modifier, state: BaseState<WeatherCardVO>) {

    UiStateMapper(state = state,
        loadingUi = { },
        errorUi = { },
        contentUi = { weather ->
            CurrentWeatherSection(
                modifier = modifier,
                location = weather.location,
                date = weather.date,
                temp = weather.temp,
                status = weather.status,
                statusIcon = weather.statusIcon,
                windySpeed = weather.windySpeed,
                uv = weather.uv,
                rain = weather.rain
            )
        }
    )
}

@EfficientPreview
@Composable
private fun CurrentWeatherSectionPreview() {
    val weather by remember {
        mutableStateOf(BaseState(data = WeatherCardVO.dummy))
    }

    EfficientWeatherAppTheme(dynamicColor = false) {
        CurrentWeatherSection(state = weather)
    }
}

@Composable
fun ColumnScope.TheNext5DaysWeatherForecastSection(
    state: BaseState<List<WeatherCardVO>>
) {
    UiStateMapper(
        state = state,
        loadingUi = {
            LoadingUi(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        },
        errorUi = { },
        contentUi = {
            Text(
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.space_small)),
                text = stringResource(R.string.lbl_five_days_forecast).uppercase(),
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )

            LazyColumn(
                contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.space_general)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_general))
            ) {
                items(it) { weather ->
                    WeatherPredicateCard(weather = weather)
                }
            }
        })
}

@EfficientPreview
@Composable
private fun TheNext5DaysWeatherForecastSectionPreview() {

    val state by remember {
        mutableStateOf(BaseState(data = (0..5).map { WeatherCardVO.dummy }))
    }

    EfficientWeatherAppTheme(dynamicColor = false) {
        Column {
            TheNext5DaysWeatherForecastSection(state = state)
        }
    }
}