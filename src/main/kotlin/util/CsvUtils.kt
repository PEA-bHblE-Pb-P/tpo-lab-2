package util

import util.Round.rounded
import java.io.BufferedWriter
import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.bufferedWriter

object CsvUtils {
    fun readCsvFileValues(fileName: String): Map<String, Double> =
        javaClass.getResourceAsStream(fileName)?.bufferedReader().use { reader ->
            val res = mutableMapOf<String, Double>()
            reader?.readLine() // Headers
            reader?.forEachLine { line ->
                val (x, y) = line.split(",").map { it.toDouble() }
                res[x.toString()] = y
            }
            return res
        }

    fun csvFileWriter(path: String, head: String) =
        Path(path).bufferedWriter().also { writer ->
            writer.write(head)
            writer.newLine()
        }

    fun BufferedWriter?.writeRounded(x: Double, result: Double) {
        this?.write("${x.rounded()},${result.rounded()}")
    }

    fun BufferedWriter?.writeRounded3(a: Double, b: Double, result: Double) {
        this?.write("${a.rounded()},${b.rounded()},${result.rounded()}")
    }
}
