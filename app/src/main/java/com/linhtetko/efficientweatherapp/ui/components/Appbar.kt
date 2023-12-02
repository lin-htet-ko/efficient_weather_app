package com.linhtetko.efficientweatherapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.linhtetko.efficientweatherapp.R
import com.linhtetko.efficientweatherapp.ui.theme.EfficientWeatherAppTheme
import com.linhtetko.efficientweatherapp.ui.utils.EfficientPreview

@Composable
fun EfficientAppbar(
    modifier: Modifier = Modifier,
    title: String,
    navigationButton: @Composable RowScope.() -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    Row(
        modifier = modifier.padding(dimensionResource(id = R.dimen.space_general)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        navigationButton()

        Text(
            text = title,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            lineHeight = MaterialTheme.typography.headlineLarge.lineHeight,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        actions()
    }
}

@EfficientPreview
@Composable
private fun EfficientAppbarPreview() {
    EfficientWeatherAppTheme(dynamicColor = false) {
        EfficientAppbar(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.app_name)
        )
    }
}