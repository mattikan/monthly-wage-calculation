package main.kotlin.fi.mattikan.rekrynut.logic

import java.lang.Double.max
import java.time.LocalDate
import kotlin.math.min

data class Workday(val date: LocalDate) {
    val wage = 4.25
    val eveningBonus = 1.25

    var totalHours: Double = 0.00
    var eveningHours: Double = 0.00

    fun addHours(totalHours: Double, eveningHours: Double) {
        this.totalHours += totalHours
        this.eveningHours += eveningHours
    }

    // overtime calculation whoo!
    // first three overtime hours are +25%
    // next one is +50%
    // after that it's +100%
    fun overtime(): Double {

//        var overtime: Double
//        when (totalHours) {
//            in 0.00..8.00 -> overtime = 0.00
//            in 8.00..11.00 -> overtime = (totalHours - 8.00) * wage * 0.25
//            in 11.00..12.00 -> overtime = (3 * wage * 0.25) + ((totalHours - 11.00) * wage * 0.50)
//            else -> overtime = (3 * wage * 0.25) + (wage * 0.50) + ((totalHours - 12.00) * wage)
//        }
//        return overtime

        var overtimeHours = max(totalHours - 8.00, 0.00)
        return ((min(overtimeHours, 3.00) * wage * 0.25)
                + (min(max(overtimeHours - 3.00, 0.00), 1.00) * wage * 0.50)
                + (max(overtimeHours - 4.00, 0.00) * wage))
    }

    // total workday's wage is regular hours + evening hours + overtime
    fun totalWage(): Double {
        return (totalHours * wage) + (eveningHours * eveningBonus) + overtime()
    }
}

