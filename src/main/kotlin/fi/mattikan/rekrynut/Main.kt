package main.kotlin.fi.mattikan.rekrynut

import main.kotlin.fi.mattikan.rekrynut.io.*
import java.io.File

fun main(args: Array<String>) {
    print("annapa pathi tiedostoon: ")
    val hourListFile = File(readLine())
    val hourList = readHourList(hourListFile)

}

fun parseFile() {

}