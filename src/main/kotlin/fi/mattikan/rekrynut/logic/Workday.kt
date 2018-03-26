package main.kotlin.fi.mattikan.rekrynut.logic

import java.time.LocalDate

data class Workday(val date: LocalDate) {

    var regularHours: Double = 0.00
    var eveningHours: Double = 0.00

    fun addHours(regularHours: Double, eveningHours: Double) {
        this.regularHours += regularHours
        this.eveningHours += eveningHours
    }

    fun overtime(): Double {
        val totalHours = regularHours + eveningHours
        var overtime: Double
        when (totalHours) {
            in 0.00..8.00 -> overtime = 0.00
            in 8.00..11.00 -> overtime = (totalHours - 8.00) * 4.25 * 0.25
            in 11.00..12.00 -> overtime = (3 * 4.25 * 0.25) + ((totalHours - 11.00) * 4.25 * 0.50)
            else -> overtime = (3 * 4.25 * 0.25) + (4.25 * 0.50) + ((totalHours - 12.00) * 4.25)
        }
        return overtime
    }

    fun totalWage(): Double {
        return (regularHours + eveningHours) * 4.25 + eveningHours * 1.25 + overtime()
    }
}

