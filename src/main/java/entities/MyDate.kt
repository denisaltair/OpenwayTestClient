package entities

import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

class MyDate: Date {
    constructor():super()
    constructor(time:Long):super(time)
    companion object {
        fun parseIso8583Date(iso8583Date:String):MyDate {
            val formatter= SimpleDateFormat("ddMMyy")
            return MyDate(formatter.parse(iso8583Date).time)

        }

        fun parseFromString(value:String, dateFormat:String ="yyyy-MM-dd'T'HH:mm:ss"):MyDate {
            val formatter=SimpleDateFormat(dateFormat)
            return MyDate(formatter.parse(value).time)
        }

    }

    fun toIso8583Date():String {
        val formatter= SimpleDateFormat("MMyyHHmmss")
        return formatter.format(this)
    }

    fun toFormat(dateFormat:String="yyyy-MM-dd'T'HH:mm:ss", locale:Locale=Locale.ROOT):String {
        val formatter= SimpleDateFormat(dateFormat)
        if (locale.country==="RUS") formatter.dateFormatSymbols=getRuLocaleDateFromatSymbols()
        return formatter.format(this)
    }



    private fun getRuLocaleDateFromatSymbols(): DateFormatSymbols {
        val locale = Locale("ru","RUS")
        val dfs: DateFormatSymbols = DateFormatSymbols.getInstance(locale)
        val months = arrayOf(
            "января", "февраля", "марта", "апреля", "мая", "июня",
            "июля", "августа", "сентября", "октября", "ноября", "декабря")
        val shortMonths = arrayOf(
            "янв", "фев", "мар", "апр", "май", "июн",
            "июл", "авг", "сен", "окт", "ноя", "дек")
        dfs.setMonths(months)
        dfs.setShortMonths(shortMonths)
        val weekdays = arrayOf("", "Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота")
        val shortWeekdays = arrayOf("", "вс", "пн", "вт", "ср", "чт", "пт", "сб")
        dfs.setWeekdays(weekdays)
        dfs.setShortWeekdays(shortWeekdays)
        return dfs

//        val sdf = SimpleDateFormat(format, locale)
        //      sdf.dateFormatSymbols = dfs
        //       return sdf.format(date) // пт, 09 декабря 2016
    }


}