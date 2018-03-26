package main.kotlin.fi.mattikan.rekrynut

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.FileChooser
import javafx.stage.Stage
import main.kotlin.fi.mattikan.rekrynut.io.*
import main.kotlin.fi.mattikan.rekrynut.logic.Employee
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

// simplest gui i could throw together quickly
class MonthlyWageCalculation : Application() {
    override fun start(primaryStage: Stage) {
        var employees: List<Employee>
        val pane = Pane()
        val scene = Scene(pane, 200.0, 200.0)
        primaryStage.scene = scene
        val fileChooser = FileChooser()
        fileChooser.title = "Open input file"
        var file = fileChooser.showOpenDialog(primaryStage)
        if (file.exists()) {
            employees = readHourlist(file)
            var path = "monthly_wages_"
            
            path += (LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMdd"))
                    + "_"
                    + LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss")))

            writeToFile(path, employeesToString(employees, file.path))
            System.exit(1)
        }
    }

}

fun main(args: Array<String>) {
    Application.launch(MonthlyWageCalculation::class.java)
}
