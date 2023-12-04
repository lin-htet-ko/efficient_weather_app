package com.linhtetko.network.vos


import com.google.gson.annotations.SerializedName

data class ErrorVO(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("message")
    val message: String? = null
)