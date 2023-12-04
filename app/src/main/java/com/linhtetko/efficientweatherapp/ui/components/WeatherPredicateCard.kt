package com.linhtetko.efficientweatherapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import com.linhtetko.efficientweatherapp.R
import com.linhtetko.efficientweatherapp.ui.theme.EfficientWeatherAppTheme
import com.linhtetko.efficientweatherapp.ui.utils.EfficientPreview
import com.linhtetko.efficientweatherapp.ui.vos.WeatherUiVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun WeatherPredicateCard(modifier: Modifier = Modifier, weather: WeatherUiVO) {

    WeatherPredicateCard(
        modifier = modifier,
        day = weather.day,
        date = weather.date,
        time = weather.time,
        temp = stringResource(id = R.string.lbl_s_degree_cel, weather.tempC),
        status = weather.status,
        windySpeed = stringResource(id = R.string.lbl_s_kmp, weather.windySpeedKph),
        uv = weather.uv,
        cloud = stringResource(id = R.string.lbl_s_percentage, weather.cloud),
        statusIcon = weather.statusIcon
    )
}

@Composable
fun WeatherPredicateCard(
    modifier: Modifier = Modifier,
    day: String,
    date: String,
    time: String,
    temp: String,
    status: String,
    statusIcon: String,
    windySpeed: String,
    uv: String,
    cloud: String
) {
    val generalPadding = (dimensionResource(id = R.dimen.space_general))
    val space2x = (dimensionResource(id = R.dimen.space_2x))

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(percent = 5),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        )
    ) {
        WeatherPredicateCardHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(space2x), day = day, date = date, time = time
        )
        Divider(
            modifier = Modifier.padding(horizontal = space2x),
            color = MaterialTheme.colorScheme.onSurface,
            thickness = dimensionResource(id = R.dimen.size_stroke_general)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = generalPadding, bottom = generalPadding + generalPadding)
                .padding(horizontal = space2x),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            WeatherDescriptiveStatus(
                modifier = Modifier.weight(1f),
                temp = temp,
                status = status,
                statusIcon = statusIcon
            )
            WeatherGeneralStatus(
                isValueRight = false, windySpeed = windySpeed, uv = uv, cloud = cloud
            )
        }
    }
}

@EfficientPreview
@Composable
private fun WeatherPredicateCardPreview() {
    EfficientWeatherAppTheme(dynamicColor = false) {
        WeatherPredicateCard(weather = WeatherUiVO.dummy)
    }
}

@Composable
fun WeatherGeneralStatus(
    modifier: Modifier = Modifier,
    isValueRight: Boolean,
    windySpeed: String,
    uv: String,
    cloud: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_2x)),
        horizontalAlignment = if (!isValueRight) AbsoluteAlignment.Right else AbsoluteAlignment.Left
    ) {
        ValueTextWithIcon(
            isValueRight = isValueRight,
            label = stringResource(id = R.string.lbl_windy_speed),
            value = windySpeed,
            icon = ImageVector.vectorResource(R.drawable.ic_windy_to_left)
        )
        ValueTextWithIcon(
            isValueRight = isValueRight,
            label = stringResource(id = R.string.lbl_uv),
            value = uv,
            icon = ImageVector.vectorResource(R.drawable.ic_uv)
        )
        ValueTextWithIcon(
            isValueRight = isValueRight,
            label = stringResource(id = R.string.lbl_cloudy),
            value = cloud,
            icon = ImageVector.vectorResource(R.drawable.ic_cloud_rain)
        )
    }
}

@EfficientPreview
@Composable
private fun WeatherGeneralStatusPreview() {
    val weather by remember {
        mutableStateOf(WeatherUiVO.dummy)
    }

    EfficientWeatherAppTheme(dynamicColor = false) {
        WeatherGeneralStatus(
            windySpeed = stringResource(id = R.string.lbl_s_kmp, weather.windySpeedKph),
            uv = weather.uv,
            cloud = stringResource(id = R.string.lbl_s_percentage, weather.cloud),
            isValueRight = true
        )
    }
}

@Composable
private fun WeatherDescriptiveStatus(
    modifier: Modifier = Modifier, temp: String, status: String, statusIcon: String
) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = temp,
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                color = MaterialTheme.colorScheme.onBackground
            )
            HorizontalSpaceSmall()
            NetworkImage(
                url = statusIcon,
                contentDescription = status,
                modifier = Modifier.size(dimensionResource(id = R.dimen.size_icon_general))
            )
        }
        Text(
            text = status,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@EfficientPreview
@Composable
private fun WeatherDescriptiveStatusPreview() {

    val weather by remember {
        mutableStateOf(WeatherUiVO.dummy)
    }

    EfficientWeatherAppTheme(dynamicColor = false) {
        WeatherDescriptiveStatus(
            temp = stringResource(id = R.string.lbl_s_kmp, weather.tempC),
            status = weather.status,
            statusIcon = weather.statusIcon
        )
    }
}

@Composable
fun WeatherPredicateCardHeader(
    modifier: Modifier = Modifier, day: String, date: String, time: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = day, color = MaterialTheme.colorScheme.onBackground)
        TextWithIcon(
            isIconOnRight = false,
            text = date,
            icon = ImageVector.vectorResource(R.drawable.ic_calendar)
        )
        TextWithIcon(
            isIconOnRight = false,
            text = time,
            icon = ImageVector.vectorResource(R.drawable.ic_access_time)
        )
    }
}

@EfficientPreview
@Composable
private fun WeatherPredicateCardHeaderPreview() {

    val weather by remember {
        mutableStateOf(WeatherUiVO.dummy)
    }

    EfficientWeatherAppTheme(dynamicColor = false) {
        WeatherPredicateCardHeader(day = weather.day, date = weather.date, time = weather.time)
    }
}

@Composable
fun WeatherDescriptiveByCenterAlign(
    modifier: Modifier = Modifier,
    statusIcon: String,
    status: String,
    temp: String,
    location: String? = null
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_general)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NetworkImage(
            url = statusIcon,
            contentDescription = status,
            modifier = Modifier.size(dimensionResource(id = R.dimen.size_icon_3x))
        )
        Text(
            text = temp,
            fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = status,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        if (location != null) TextWithIcon(
            isIconOnRight = false,
            text = location,
            icon = Icons.Default.LocationOn,
        )
    }
}

@EfficientPreview
@Composable
private fun WeatherDescriptiveByCenterAlignPreview() {

    val weather = WeatherUiVO.dummy

    EfficientWeatherAppTheme(dynamicColor = false) {
        WeatherDescriptiveByCenterAlign(
            statusIcon = weather.statusIcon,
            status = weather.status,
            temp = stringResource(id = R.string.lbl_s_kmp, weather.tempC)
        )
    }
}