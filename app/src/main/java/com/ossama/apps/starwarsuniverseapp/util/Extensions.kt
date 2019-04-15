package com.ossama.apps.starwarsuniverseapp.util

fun String.retrieveId(): String {
    val newString = if (this.lastIndex.equals('/')) this.substring(0, this.length) else this
    return newString.substringAfterLast("/", "")
}