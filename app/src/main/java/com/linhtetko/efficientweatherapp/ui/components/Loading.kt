package com.linhtetko.efficientweatherapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.linhtetko.efficientweatherapp.R
import com.linhtetko.efficientweatherapp.ui.theme.EfficientWeatherAppTheme

@Composable
fun LoadingUi(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.space_2x)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        VerticalSpaceGeneral()
        Text(text = stringResource(id = R.string.lbl_loading))
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingUiPreview() {
    EfficientWeatherAppTheme(dynamicColor = false) {
        LoadingUi()
    }
}