package com.linhtetko.network.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.linhtetko.network.vos.ErrorVO

object JsonParser {

    private val gson = Gson()

    fun parseError(json: String): ErrorVO? {
        return gson.fromJson<ErrorVO>(json, object : TypeToken<ErrorVO>() {}.type)
    }
}