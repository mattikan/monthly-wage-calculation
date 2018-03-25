package main.kotlin.fi.mattikan.rekrynut.logic

import java.time.LocalDate

data class Workday (val date: LocalDate) {

    var regularHours: Double = 0.00
    var eveningHours: Double = 0.00

    fun addHours(hours: Double, eveningHours: Double) {
        this.regularHours += hours
        this.eveningHours += eveningHours
    }

    fun overtime(): Double {
        // todo: calculate overtime compensation
        return 0.00
    }

    fun totalWage(): Double {
        // todo: regular + evening + overtime
        return ((this.regularHours + this.eveningHours)*4.25 + this.eveningHours*1.25) //todo: plus overtime
    }
}

