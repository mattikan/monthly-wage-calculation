package main.kotlin.fi.mattikan.rekrynut.io

import main.kotlin.fi.mattikan.rekrynut.logic.Employee
import java.io.File
import java.math.RoundingMode

fun writeToFile(path: String, content: String) {
    var file = File(path)
    if (file.exists()) {
        println("File already exists! Aborting.")
        return
    }
    try {
        file.printWriter().use {
            it.print(content)
        }
    } catch (e: Exception) {
        println(e.localizedMessage)
        println("Something went wrong!")
    }
}

// just returns a nice output string for writing to file
fun employeesToString(employees: List<Employee>, path: String): String {
    var output = ""
    output += "Wages calculated from: $path \n"
    var employeesSorted = employees.sortedBy { it.ID }
    for (employee in employeesSorted) {
        println(employee)
        println("Days worked: ${employee.workdays.size}")
        val hours = employee.workdays.sumByDouble { it.totalHours + it.eveningHours }
        println("Hours worked: $hours, which includes ${employee.workdays.sumByDouble { it.eveningHours }} evening compensation hours")
        val dosh = employee.workdays.sumByDouble { it.totalWage() }.toBigDecimal().setScale(2, RoundingMode.HALF_UP)
        println("Dosh earned: ${dosh}")
        output += "${employee.ID}, ${employee.name}, \$$dosh \n"
    }
    return output
}