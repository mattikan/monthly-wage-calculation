package main.kotlin.fi.mattikan.rekrynut

import main.kotlin.fi.mattikan.rekrynut.io.*
import main.kotlin.fi.mattikan.rekrynut.logic.Employee
import java.math.RoundingMode
import java.time.LocalDateTime

fun main(args: Array<String>) {
    var employees: List<Employee>

    print("give path to csv: ")
    var path = readLine()

    if (!path.isNullOrEmpty()) {
        employees = readHourlist(path!!)
    } else {
        path = "res/hourlist.csv"
        println("empty path given, using default\n")
        employees = readHourlist(path)
    }

    var output = ""
    output += "Wages calculated from: $path \n"
    employees = employees.sortedBy { it.ID }
    for (employee in employees) {
        println(employee)
        println("Days worked: ${employee.workdays.size}")
        val hours = employee.workdays.sumByDouble { it.regularHours + it.eveningHours }
        println("Hours worked: $hours, which includes ${employee.workdays.sumByDouble { it.eveningHours }} evening compensation hours")
        val dosh = employee.workdays.sumByDouble { it.totalWage() }.toBigDecimal().setScale(2, RoundingMode.HALF_UP)
        println("Dosh earned: ${dosh}")

        output += "${employee.ID}, ${employee.name}, \$$dosh \n"
    }

    writeToFile("monthly_wages_${LocalDateTime.now().nano}", output)

//    var dtf = DateTimeFormatter.ofPattern("HH:mm")
//    var time1 = LocalTime.parse("23:00", dtf)
//    var time2 = LocalTime.parse("00:00", dtf)
//    println(time1.until(time2, MINUTES)/60.00)
//    println(MINUTES.between(time1, time2)/60.00)


    // todo: all of output
}