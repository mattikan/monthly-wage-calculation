package main.kotlin.fi.mattikan.rekrynut.logic

import java.time.LocalDate

data class Workday(val date: LocalDate) {
    val wage = 4.25
    val eveningBonus = 1.25

    var totalHours: Double = 0.00
    var eveningHours: Double = 0.00

    fun addHours(totalHours: Double, eveningHours: Double) {
        this.totalHours += totalHours
        this.eveningHours += eveningHours
    }

    fun overtime(): Double {
        var overtime: Double
        when (totalHours) {
            in 0.00..8.00 -> overtime = 0.00
            in 8.00..11.00 -> overtime = (totalHours - 8.00) * wage * 0.25
            in 11.00..12.00 -> overtime = (3 * wage * 0.25) + ((totalHours - 11.00) * wage * 0.50)
            else -> overtime = (3 * wage * 0.25) + (wage * 0.50) + ((totalHours - 12.00) * wage)
        }
        return overtime
    }

    fun totalWage(): Double {
        return (totalHours * wage) + (eveningHours * eveningBonus) + overtime()
    }
}

