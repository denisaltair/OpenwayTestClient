package other

import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun getDateFromString(value:String, dateFormat:String ="yyyy-MM-dd'T'HH:mm:ss"): Date {
        val formatter= SimpleDateFormat(dateFormat)
        return Date(formatter.parse(value).time)
    }

    fun dateToFormat(value:Date, dateFormat:String="yyyy-MM-dd'T'HH:mm:ss", locale:Locale=Locale.ROOT):String {
        val formatter= SimpleDateFormat(dateFormat)
        if (locale.country==="RUS") formatter.dateFormatSymbols=getRuLocaleDateFromatSymbols()
        return formatter.format(value)
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
    }



}