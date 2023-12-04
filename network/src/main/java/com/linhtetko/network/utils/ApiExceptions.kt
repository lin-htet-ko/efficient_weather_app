package com.linhtetko.network.utils

internal class ClientException(message: String): Exception(message)

internal class ServerException(message: String): Exception(message)

internal class IdleException(message: String): Exception(message)