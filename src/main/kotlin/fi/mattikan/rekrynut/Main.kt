package main.kotlin.fi.mattikan.rekrynut

import main.kotlin.fi.mattikan.rekrynut.io.*
import main.kotlin.fi.mattikan.rekrynut.logic.Employee
import main.kotlin.fi.mattikan.rekrynut.logic.Workday
import java.io.File
import java.math.RoundingMode
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit.MINUTES

fun main(args: Array<String>) {

/*
    val hourListFile = File(readLine())
    val hourList = readFile(hourListFile)

    var testday = Workday(LocalDate.now(), 4.25, 4.50)
    testday.addHours(4.25, 4.50)
    println(testday.totalWage())

*/

    print("give path to csv: ")
    val path = readLine()
    if (!path.isNullOrEmpty()) {
        readHourlist(path!!)
    } else {
        println("empty path given, using default\n")
        val employees = readHourlist("res/hourlist.csv")
        for (employee in employees) {
            println(employee)
            println("Days worked: ${employee.workdays.size}")
            val hours = employee.workdays.sumByDouble { it.regularHours + it.eveningHours }
            println("Hours worked: $hours, which includes ${employee.workdays.sumByDouble { it.eveningHours }} evening compensation hours")
            val dosh = employee.workdays.sumByDouble { it.totalWage() }
            println("Dosh earned: ${dosh.toBigDecimal().setScale(2, RoundingMode.HALF_UP)}")
        }
    }

//    var dtf = DateTimeFormatter.ofPattern("HH:mm")
//    var time1 = LocalTime.parse("23:00", dtf)
//    var time2 = LocalTime.parse("00:00", dtf)
//    println(time1.until(time2, MINUTES)/60.00)
//    println(MINUTES.between(time1, time2)/60.00)


    // todo: all of output
}

fun parseFile() {

}