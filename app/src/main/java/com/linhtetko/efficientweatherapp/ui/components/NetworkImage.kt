package com.linhtetko.efficientweatherapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.linhtetko.efficientweatherapp.R

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String? = null,
    @DrawableRes placeholder: Int = R.drawable.ic_cloud_rain
) {
    AsyncImage(
        modifier = modifier.background(Color.Transparent),
        model = url.prependIndent("https:"),
        contentDescription = contentDescription,
        placeholder = painterResource(id = placeholder),
    )
}