package com.linhtetko.efficientweatherapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.linhtetko.efficientweatherapp.R

private val UrbanistFontFamily = FontFamily(
    Font(resId = R.font.urbanist_regular, FontWeight.Light),
    Font(resId = R.font.urbanist_medium, FontWeight.Medium),
    Font(resId = R.font.urbanist_bold, FontWeight.Bold)
)
private val defaultTypography = Typography()

val Typography = Typography(
    headlineLarge = defaultTypography.headlineLarge.copy(
        fontFamily = UrbanistFontFamily
    ),
    headlineMedium = defaultTypography.headlineMedium.copy(
        fontFamily = UrbanistFontFamily
    ),
    headlineSmall = defaultTypography.headlineSmall.copy(
        fontFamily = UrbanistFontFamily
    ),

    titleLarge = defaultTypography.titleLarge.copy(
        fontFamily = UrbanistFontFamily
    ),
    titleMedium = defaultTypography.titleMedium.copy(
        fontFamily = UrbanistFontFamily
    ),
    titleSmall = defaultTypography.titleSmall.copy(
        fontFamily = UrbanistFontFamily
    ),

    bodyLarge = defaultTypography.bodyLarge.copy(
        fontFamily = UrbanistFontFamily
    ),
    bodyMedium = defaultTypography.bodyMedium.copy(
        fontFamily = UrbanistFontFamily
    ),
    bodySmall = defaultTypography.bodySmall.copy(
        fontFamily = UrbanistFontFamily
    ),

    displayLarge = defaultTypography.displayLarge.copy(
        fontFamily = UrbanistFontFamily
    ),
    displayMedium = defaultTypography.displayMedium.copy(
        fontFamily = UrbanistFontFamily
    ),
    displaySmall = defaultTypography.displaySmall.copy(
        fontFamily = UrbanistFontFamily
    ),

    labelLarge = defaultTypography.labelLarge.copy(
        fontFamily = UrbanistFontFamily
    ),
    labelMedium = defaultTypography.labelMedium.copy(
        fontFamily = UrbanistFontFamily
    ),
    labelSmall = defaultTypography.labelSmall.copy(
        fontFamily = UrbanistFontFamily
    )
)