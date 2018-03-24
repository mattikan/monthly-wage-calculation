package main.kotlin.fi.mattikan.rekrynut

import main.kotlin.fi.mattikan.rekrynut.io.*
import main.kotlin.fi.mattikan.rekrynut.logic.Workday
import java.io.File
import java.time.LocalDate

fun main(args: Array<String>) {
    //print("annapa pathi tiedostoon: ")
    //val hourListFile = File(readLine())
    //val hourList = readFile(hourListFile)

    var testday = Workday(LocalDate.now(), 4.25, 4.50)
    testday.addHours(4.25, 4.50)
    println(testday.totalWage())
}

fun parseFile() {

}