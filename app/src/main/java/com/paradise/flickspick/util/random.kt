package com.paradise.flickspick.util

fun randomInt(): Int {
    return (0..100).random()
}

fun randomProgress(): Float {
    return randomInt().toFloat() / 100
}