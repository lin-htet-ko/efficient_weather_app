package com.linhtetko.domain.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateManager {

    private fun getCalendar() = Calendar.getInstance()

    private fun getDateFormatter(pattern: String) = SimpleDateFormat(pattern, Locale.getDefault())

    fun parse(
        time: String?,
        from: String = "yyyy-MM-dd hh:mm",
        to: String = "dd/MM/yyyy"
    ): String? {
        val fromFormat = getDateFormatter(from)
        val toFormat = getDateFormatter(to)

        val fromDate = if (time != null) fromFormat.parse(time) else null

        return if (fromDate != null) toFormat.format(fromDate) else "-"
    }
}