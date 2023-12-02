package com.linhtetko.efficientweatherapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.linhtetko.efficientweatherapp.R
import com.linhtetko.efficientweatherapp.ui.theme.EfficientWeatherAppTheme
import com.linhtetko.efficientweatherapp.ui.utils.EfficientPreview


@Composable
fun ErrorMessage(modifier: Modifier = Modifier, message: String) {
    Text(
        text = message,
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.space_2x))
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary
    )
}

@EfficientPreview
@Composable
private fun ErrorMessagePreview() {
    EfficientWeatherAppTheme(dynamicColor = false) {
        ErrorMessage(message = stringResource(id = R.string.lbl_no_internet_connection))
    }
}

@Composable
fun TextWithIcon(
    modifier: Modifier = Modifier,
    isIconOnRight: Boolean,
    text: String,
    textSize: TextUnit = MaterialTheme.typography.bodySmall.fontSize,
    icon: ImageVector,
    iconSize: Dp = dimensionResource(id = R.dimen.size_icon_small),
) {
    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.primary) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            if (!isIconOnRight) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = icon,
                    contentDescription = text
                )
                HorizontalSpaceGeneral()
            }

            Text(text = text, color = MaterialTheme.colorScheme.onBackground, fontSize = textSize)

            if (isIconOnRight) {
                HorizontalSpaceGeneral()
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = icon,
                    contentDescription = text
                )
            }
        }
    }
}

@EfficientPreview
@Composable
private fun TextWithIconPreview() {
    EfficientWeatherAppTheme(dynamicColor = false) {
        TextWithIcon(
            isIconOnRight = true,
            text = "Yangon",
            icon = Icons.Default.LocationOn
        )
    }
}

@Composable
fun ValueTextWithIcon(
    modifier: Modifier = Modifier,
    isValueRight: Boolean,
    label: String,
    value: String,
    icon: ImageVector
) {
    val onSurface = MaterialTheme.colorScheme.onBackground
    val valueFontWeight = MaterialTheme.typography.headlineLarge.fontWeight
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        if (!isValueRight) {
            Text(
                text = value,
                fontWeight = valueFontWeight,
                color = onSurface
            )
            HorizontalSpaceGeneral()
        }

        TextWithIcon(isIconOnRight = !isValueRight, text = label, icon = icon)

        if (isValueRight) {
            HorizontalSpaceGeneral()
            Text(
                text = value,
                fontWeight = valueFontWeight,
                color = onSurface
            )
        }
    }
}

@EfficientPreview
@Composable
private fun ValueTextWithIconPreview() {
    EfficientWeatherAppTheme(dynamicColor = false) {
        ValueTextWithIcon(
            isValueRight = true,
            label = stringResource(id = R.string.lbl_windy_speed),
            value = "6.1 kmp",
            icon = ImageVector.vectorResource(R.drawable.ic_windy_to_right)
        )
    }
}