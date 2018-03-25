package main.kotlin.fi.mattikan.rekrynut.io

import main.kotlin.fi.mattikan.rekrynut.logic.Employee
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

// pretty self-explanatory what this does
fun readFile(hourlist: File): String {
    val inputStream = hourlist.inputStream().bufferedReader().use { it.readText() }
    return inputStream
}

// this is where the heavy crunching happens
fun readHourlist(path: String): List<Employee> {
    var employees = ArrayList<Employee>()
    try {

        // time to do all the reading in of the hour list
        var hourlist = readFile(File(path)).split("\n")

        // process the input line by line
        // if employee exists: just push the line to its addShift
        // if employee doesn't exist: create it and push the line to its addShift
        // simple, easy and fun
        for (line in hourlist) {

            var lineSplit = line.split(",")
            if (!checkLineValidity(lineSplit)) {
                println("Line is malformed, skipping")
                continue
            }
            var found = employees.find { it.ID.equals(lineSplit[0]) && it.name.equals(lineSplit[1]) }

            if (found != null) {
                found.newShift(lineSplit[2], lineSplit[3], lineSplit[4])
            } else {
                found = Employee(lineSplit[0], lineSplit[1])
                employees.add(found)
                found.newShift(lineSplit[2], lineSplit[3], lineSplit[4])
            }

        }
    } catch (e: Exception) {
        println(e.stackTrace)
        println("Something went wrong with reading the file.")
    }

    for (employee in employees) {
        println(employee)
    }
    return employees
}

fun checkLineValidity(line: List<String>): Boolean {
    return (line.size == 5
            && line[2].split(".").size == 3
            && line[3].matches(Regex("(?:[01]?[0-9]|[2][0-3]):?[0-5]\\d"))
            && line[4].matches(Regex("(?:[01]?[0-9]|[2][0-3]):?[0-5]\\d"))
            )
}