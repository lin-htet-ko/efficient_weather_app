package com.linhtetko.efficientweatherapp.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.linhtetko.efficientweatherapp.R

@Composable
fun HorizontalSpaceSmall() {
    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.space_small)))
}

@Composable
fun HorizontalSpaceGeneral() {
    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.space_general)))
}

@Composable
fun VerticalSpaceGeneral() {
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_general)))
}