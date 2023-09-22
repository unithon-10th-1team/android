package com.paradise.flickspick.util

fun randomString(length: Int): String {
    val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZ"
    return (1..length)
        .map { charset.random() }
        .joinToString("")
}