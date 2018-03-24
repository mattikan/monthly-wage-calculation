package main.kotlin.fi.mattikan.rekrynut

import main.kotlin.fi.mattikan.rekrynut.io.*
import main.kotlin.fi.mattikan.rekrynut.logic.Workday
import java.io.File
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit.MINUTES

fun main(args: Array<String>) {

/*
    print("annapa pathi tiedostoon: ")
    val hourListFile = File(readLine())
    val hourList = readFile(hourListFile)
*/

    var testday = Workday(LocalDate.now(), 4.25, 4.50)
    testday.addHours(4.25, 4.50)
    println(testday.totalWage())

    var time1 = LocalTime.parse("23:00", DateTimeFormatter.ofPattern("HH:mm"))
    var time2 = LocalTime.parse("01:00", DateTimeFormatter.ofPattern("HH:mm"))

    println(time1.until(time2, MINUTES)/60.00)
    println(MINUTES.between(time1, time2)/60.00)
}

fun parseFile() {

}