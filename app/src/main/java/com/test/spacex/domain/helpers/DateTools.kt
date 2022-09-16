package com.test.spacex.domain.helpers

import java.text.SimpleDateFormat
import java.util.*

fun String.toFormattedDate(): String {
    return try {
        val sourceFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        val date = sourceFormat.parse(this)
        val destinationFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.US)
        val parsedDate = date?.let { destinationFormat.format(it) }
        parsedDate ?: "Invalid Date"
    } catch (e: Exception) {
        "Invalid Date"
    }
}