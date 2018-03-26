package main.kotlin.fi.mattikan.rekrynut

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.FileChooser
import javafx.stage.Stage
import main.kotlin.fi.mattikan.rekrynut.io.*
import main.kotlin.fi.mattikan.rekrynut.logic.Employee

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
            val output = employeesToString(employees, file.path)
            writeToFile("monthly_wages_${System.currentTimeMillis()}", output)
        }
    }

}

fun main(args: Array<String>) {
    Application.launch(MonthlyWageCalculation::class.java)
}
