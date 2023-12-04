package com.linhtetko.efficientweatherapp.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.linhtetko.efficientweatherapp.R
import com.linhtetko.efficientweatherapp.ui.components.EfficientAppbar
import com.linhtetko.efficientweatherapp.ui.components.ErrorMessage
import com.linhtetko.efficientweatherapp.ui.components.LoadingUi
import com.linhtetko.efficientweatherapp.ui.components.VerticalSpace2x
import com.linhtetko.efficientweatherapp.ui.components.VerticalSpaceGeneral
import com.linhtetko.efficientweatherapp.ui.components.WeatherDescriptiveByCenterAlign
import com.linhtetko.efficientweatherapp.ui.components.WeatherGeneralStatus
import com.linhtetko.efficientweatherapp.ui.components.WeatherPredicateCardHeader
import com.linhtetko.efficientweatherapp.ui.screens.base.BaseState
import com.linhtetko.efficientweatherapp.ui.screens.base.UiStateMapper
import com.linhtetko.efficientweatherapp.ui.theme.EfficientWeatherAppTheme
import com.linhtetko.efficientweatherapp.ui.utils.EfficientPreview
import com.linhtetko.efficientweatherapp.ui.vos.WeatherUiVO

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel,
    onTapNavigate: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    SearchScreen(
        modifier = modifier,
        state = viewModel.state,
        keyword = viewModel.keyword,
        onKeywordChange = viewModel::onKeywordChange,
        onTapSearch = {
            focusManager.clearFocus()
            viewModel.search()
        },
        onTapNavigate = onTapNavigate
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    state: BaseState<SearchScreenState>,
    keyword: String,
    onKeywordChange: (String) -> Unit,
    onTapSearch: () -> Unit,
    onTapNavigate: () -> Unit
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = dimensionResource(
                    id = R.dimen.space_general
                )
            ),
        topBar = {
            EfficientAppbar(title = stringResource(id = R.string.lbl_search), navigationButton = {
                IconButton(
                    onClick = onTapNavigate
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_round_chevron_left),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = dimensionResource(id = R.dimen.space_2x))

        ) {
            SearchBar(value = keyword, onValueChange = onKeywordChange, onTapSearch = onTapSearch)

            UiStateMapper(
                state = state,
                loadingUi = { LoadingUi(modifier = Modifier.weight(1f)) },
                errorUi = { message ->
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        ErrorMessage(
                            message = message
                        )
                    }
                },
                contentUi = { data -> WeatherSearchByCityCard(weather = data.weather) })
        }
    }
}

@EfficientPreview
@Composable
private fun SearchScreenPreview() {
    EfficientWeatherAppTheme(dynamicColor = false) {
        SearchScreen(
            state = BaseState(data = SearchScreenState(WeatherUiVO.dummy)),
            keyword = "",
            onKeywordChange = {},
            onTapSearch = { },
            onTapNavigate = {})
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onTapSearch: () -> Unit
) {

    val containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
    val primaryColor = MaterialTheme.colorScheme.primary
    val transparent = Color.Transparent

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        placeholder = {
            Text(text = stringResource(R.string.lbl_enter_the_city_name))
        },
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            focusedIndicatorColor = transparent,
            unfocusedIndicatorColor = transparent,
            focusedPlaceholderColor = primaryColor,
            unfocusedPlaceholderColor = primaryColor
        ),
        shape = CircleShape,
        trailingIcon = {
            IconButton(onClick = onTapSearch) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.lbl_search),
                    tint = primaryColor
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = { onTapSearch() })
    )
}

@EfficientPreview
@Composable
private fun SearchBarPreview() {

    EfficientWeatherAppTheme(dynamicColor = false) {
        SearchBar(value = "", onValueChange = {}, onTapSearch = {})
    }
}

@Composable
fun ColumnScope.WeatherSearchByCityCard(modifier: Modifier = Modifier, weather: WeatherUiVO) {
    Text(
        modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.space_2x)),
        text = stringResource(R.string.lbl_search_result).uppercase(),
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.titleMedium.fontSize,
        color = MaterialTheme.colorScheme.onBackground
    )
    WeatherSearchByCityCard(
        modifier = modifier,
        statusIcon = weather.statusIcon,
        status = weather.status,
        temp = stringResource(id = R.string.lbl_s_degree_cel, weather.tempC),
        day = weather.day,
        date = weather.date,
        time = weather.time,
        windySpeed = stringResource(id = R.string.lbl_s_kmp, weather.windySpeedKph),
        uv = weather.uv,
        cloud = stringResource(id = R.string.lbl_s_percentage, weather.cloud),
        city = weather.location
    )
}

@Composable
private fun WeatherSearchByCityCard(
    modifier: Modifier = Modifier,
    statusIcon: String,
    status: String,
    temp: String,
    day: String,
    date: String,
    time: String,
    windySpeed: String,
    uv: String,
    cloud: String,
    city: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(percent = 5))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
            .padding(dimensionResource(id = R.dimen.space_2x)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WeatherDescriptiveByCenterAlign(
            modifier = Modifier.fillMaxWidth(),
            statusIcon = statusIcon,
            status = status,
            temp = temp
        )
        VerticalSpaceGeneral()
        Text(text = city, color = MaterialTheme.colorScheme.onBackground)
        VerticalSpace2x()
        WeatherPredicateCardHeader(
            modifier = Modifier.fillMaxWidth(),
            day = day,
            date = date,
            time = time
        )
        VerticalSpace2x()
        WeatherGeneralStatus(
            modifier = Modifier.align(Alignment.Start),
            isValueRight = true,
            windySpeed = windySpeed,
            uv = uv,
            cloud = cloud
        )
    }
}

@EfficientPreview
@Composable
private fun WeatherSearchByCityCardPreview() {
    EfficientWeatherAppTheme(dynamicColor = false) {
        Column {
            WeatherSearchByCityCard(weather = WeatherUiVO.dummy)
        }
    }
}