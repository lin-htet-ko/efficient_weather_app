package com.linhtetko.domain.utils.extensions

fun  Double?.orInvalid(): Double = this ?: -1.0

fun  Int?.orInvalid(): Int = this ?: -1