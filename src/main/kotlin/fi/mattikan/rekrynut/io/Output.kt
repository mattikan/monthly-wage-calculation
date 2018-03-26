package main.kotlin.fi.mattikan.rekrynut.io

import java.io.File

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
    } catch(e: Exception) {
        println(e.localizedMessage)
        println("Something went wrong!")
    }
}