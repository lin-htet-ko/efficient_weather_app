package com.linhtetko.efficientweatherapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.linhtetko.efficientweatherapp.R

private val UrbanistFontFamily = FontFamily(
    Font(resId = R.font.urbanist_regular, FontWeight.ExtraLight),
    Font(resId = R.font.urbanist_regular, FontWeight.Light),
    Font(resId = R.font.urbanist_regular, FontWeight.Thin),
    Font(resId = R.font.urbanist_medium, FontWeight.Normal),
    Font(resId = R.font.urbanist_medium, FontWeight.Medium),
    Font(resId = R.font.urbanist_bold, FontWeight.Bold),
    Font(resId = R.font.urbanist_bold, FontWeight.SemiBold),
    Font(resId = R.font.urbanist_bold, FontWeight.ExtraBold),
    Font(resId = R.font.urbanist_bold, FontWeight.Black),
    Font(resId = R.font.urbanist_regular, FontWeight.W100),
    Font(resId = R.font.urbanist_regular, FontWeight.W200),
    Font(resId = R.font.urbanist_regular, FontWeight.W300),
    Font(resId = R.font.urbanist_medium, FontWeight.W400),
    Font(resId = R.font.urbanist_medium, FontWeight.W500),
    Font(resId = R.font.urbanist_bold, FontWeight.W600),
    Font(resId = R.font.urbanist_bold, FontWeight.W700),
    Font(resId = R.font.urbanist_bold, FontWeight.W800),
    Font(resId = R.font.urbanist_bold, FontWeight.W900),
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