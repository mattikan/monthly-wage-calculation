package main.kotlin.fi.mattikan.rekrynut.logic

import java.lang.Double.max
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.time.temporal.ChronoUnit.MINUTES

data class Employee(val ID: String, val name: String) {

    // by not declaring workdays in the header, we can use kotlin's data class automagics for stuff in Input.kt
    var workdays: ArrayList<Workday> = ArrayList<Workday>()

    // we basically chuck one line of hourlist.csv at this function, or at least the relevant bits
    // what can i say, it's a mess. work in progress.
    fun newShift(date: String, start: String, end: String) {
        val dtf = DateTimeFormatter.ofPattern("H:mm")
        val morning = LocalTime.parse("06:00", dtf)
        val evening = LocalTime.parse("19:00", dtf)
        val midnight = LocalTime.parse("00:00", dtf)

        val splitDate = date.split('.')
        val date = LocalDate.of(splitDate[2].toInt(), splitDate[1].toInt(), splitDate[0].toInt())

        val starttime = LocalTime.parse(start, dtf)
        val endtime = LocalTime.parse(end, dtf)

        var totalHours = 0.00
        var eveningHours = 0.00

        if (starttime.isBefore(endtime)) {
            totalHours += hoursFromTimeToTime(starttime, endtime)
            if (!endtime.isAfter(morning)) {
                eveningHours += hoursFromTimeToTime(starttime, endtime)
            } else if (!starttime.isBefore(evening)) {
                eveningHours += totalHours
            } else {
                eveningHours += max(hoursFromTimeToTime(evening, endtime), 0.00)
            }
        } else {
            totalHours += (hoursFromTimeToTime(starttime, midnight) + 24.00) + hoursFromTimeToTime(midnight, endtime)
            if (starttime.isBefore(morning)) {
                eveningHours += hoursFromTimeToTime(starttime, morning) + 5.00 + hoursFromTimeToTime(midnight, endtime)
            } else if (!starttime.isAfter(evening)) {
                eveningHours += 5.00
                if (!endtime.isBefore(morning)) {
                    eveningHours += 6.00
                } else {
                    eveningHours += hoursFromTimeToTime(midnight, endtime)
                }
            } else { // start time IS after evening
                eveningHours += hoursFromTimeToTime(starttime, midnight) + 24.00
                if (!endtime.isBefore(morning)) {
                    eveningHours += 6.00
                } else {
                    eveningHours += hoursFromTimeToTime(midnight, endtime)
                }
            }
        }
        println("regular hours: $totalHours, evening hours: $eveningHours\n")
        if ((totalHours < 0.00 || eveningHours < 0.00) || (eveningHours > totalHours)) {
            throw Exception()
        }

        var workday = workdays.find { it.date == date }
        if (workday == null) {
            workday = Workday(date)
            workdays.add(workday)
        }
        workday.addHours(totalHours, eveningHours)
    }
}

fun hoursFromTimeToTime(from: LocalTime, to: LocalTime): Double {
    return from.until(to, MINUTES) / 60.00
}