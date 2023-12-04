package com.linhtetko.efficientweatherapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.linhtetko.efficientweatherapp.R
import com.linhtetko.efficientweatherapp.ui.theme.EfficientWeatherAppTheme
import com.linhtetko.efficientweatherapp.ui.utils.EfficientPreview

@Composable
fun DangerAlertMessage(
    modifier: Modifier = Modifier,
    visible: Boolean,
    message: String,
    onDismiss: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically() ,
        exit =  slideOutVertically() + fadeOut()
    ) {
        Row(
            modifier = modifier
                .background(MaterialTheme.colorScheme.error.copy(alpha = 0.5f))
                .padding(dimensionResource(id = R.dimen.space_general)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalSpaceGeneral()
            Text(
                modifier = Modifier.weight(1f),
                text = message,
                color = MaterialTheme.colorScheme.onBackground
            )
            IconButton(onClick = onDismiss) {
                Icon(
                    imageVector = Icons.Rounded.Clear,
                    contentDescription = stringResource(id = R.string.lbl_clear)
                )
            }
        }
    }
}

@EfficientPreview
@Composable
private fun AlertMessagePreview() {
    EfficientWeatherAppTheme(dynamicColor = false) {
        DangerAlertMessage(
            modifier = Modifier.fillMaxWidth(),
            visible = true,
            message = "Alert Message",
            onDismiss = {})
    }
}