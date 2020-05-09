package kz.anuaryespenbet.baqylafacerecognition.utils

import java.text.SimpleDateFormat
import java.util.*

fun getDayMonthYearWithDots(year: Int, month: Int, day: Int): String {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)
    val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale("ru"))
    return simpleDateFormat.format(calendar.time)
}

fun getYearMonthDayWithDash(year: Int, month: Int, day: Int): String {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale("ru"))
    return simpleDateFormat.format(calendar.time)
}