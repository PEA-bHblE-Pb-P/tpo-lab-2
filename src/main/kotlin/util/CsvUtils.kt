package util

import kotlin.io.path.Path
import kotlin.io.path.bufferedWriter

object CsvUtils {
    fun readCsvFileValues(fileName: String): Map<Double, Double> =
        javaClass.getResourceAsStream(fileName)?.bufferedReader().use { reader ->
            val res = mutableMapOf<Double, Double>()
            reader?.readLine() // Headers
            reader?.forEachLine { line ->
                val (x, y) = line.split(",").map { it.toDouble() }
                res[x] = y
            }
            return res
        }

    fun csvFileWriter(path: String, head: String) =
        Path(path).bufferedWriter().also { writer ->
            writer.write(head)
            writer.newLine()
        }
}
