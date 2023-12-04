package com.linhtetko.network.utils

class NativeStore {

    external fun baseUrlFromJNI(): String

    external fun apiKeyFromJNI(): String

    companion object {
        init {
            System.loadLibrary("network")
        }
    }
}