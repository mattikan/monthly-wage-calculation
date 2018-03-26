package main.kotlin.fi.mattikan.rekrynut.logic

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

        var regularHours = 0.00
        var eveningHours = 0.00
        if (endtime.isAfter(starttime)) { // shift didn't cross midnight
            if (starttime.isBefore(morning)) {
                if (endtime.isBefore(morning)) {
                    eveningHours += hoursFromTimeToTime(starttime, endtime)
                } else if (!endtime.isAfter(evening)) {
                    eveningHours += hoursFromTimeToTime(starttime, morning)
                    regularHours += hoursFromTimeToTime(morning, endtime)
                } else {
                    eveningHours += hoursFromTimeToTime(starttime, morning)
                    regularHours += hoursFromTimeToTime(morning, evening)
                    eveningHours += hoursFromTimeToTime(evening, endtime)
                }
            } else if (starttime.isBefore(evening)) {
                if (endtime.isAfter(evening)) {
                    regularHours += hoursFromTimeToTime(starttime, evening)
                    eveningHours += hoursFromTimeToTime(evening, endtime)
                } else {
                    regularHours += hoursFromTimeToTime(starttime, endtime)
                }
            } else {
                eveningHours += hoursFromTimeToTime(starttime, endtime)
            }
        } else { // shift crossed midnight :(
            if (starttime.isBefore(evening)) {
                regularHours += hoursFromTimeToTime(starttime, evening)
                eveningHours += hoursFromTimeToTime(evening, midnight) + 24.00
                if (endtime.isAfter(morning)) {
                    regularHours += hoursFromTimeToTime(morning, endtime)
                    eveningHours += hoursFromTimeToTime(midnight, morning)
                } else {
                    eveningHours += hoursFromTimeToTime(midnight, endtime)
                }
            } else { // starttime is after evening and endtime is after midnight
                eveningHours += hoursFromTimeToTime(starttime, midnight) + 24.00
                if (endtime.isAfter(morning)) {
                    eveningHours += hoursFromTimeToTime(midnight, morning)
                    if (endtime.isAfter(evening)) {
                        regularHours += hoursFromTimeToTime(morning, evening)
                        eveningHours += hoursFromTimeToTime(evening, endtime)
                    } else {
                        regularHours += hoursFromTimeToTime(morning, endtime)
                    }
                } else {
                    eveningHours += hoursFromTimeToTime(midnight, endtime)
                }
            }
        }
        println("regular hours: $regularHours, evening hours: $eveningHours\n")
        if (regularHours < 0.00 || eveningHours < 0.00) {
            throw Exception()
        }

        var workday = workdays.find { it.date == date }
        if (workday == null) {
            workday = Workday(date)
            workdays.add(workday)
        }
        workday.addHours(regularHours, eveningHours)
    }
}

fun hoursFromTimeToTime(from: LocalTime, to: LocalTime): Double {
    return from.until(to, MINUTES) / 60.00
}