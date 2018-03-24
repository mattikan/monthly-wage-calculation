package main.kotlin.fi.mattikan.rekrynut.io

import java.io.File
import java.io.InputStream



fun readFile(hourList: File): String {
    val inputStream: InputStream = hourList.inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }
    return inputString
}

