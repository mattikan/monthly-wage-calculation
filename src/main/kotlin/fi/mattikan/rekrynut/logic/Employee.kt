package main.kotlin.fi.mattikan.rekrynut.logic

import java.time.LocalDate

data class Employee (val ID: String, val name: String) {

    // by not declaring workdays in the header, we can use kotlin's data class automagics for stuff in Input.kt
    var workdays: ArrayList<Workday> = ArrayList<Workday>()

    // we basically chuck one line of hourlist.csv at this function, all chopped up
    fun newShift(date: String, start: String, end: String) {
        // TODO
    }


}