package util

object CsvUtils {
    fun readCsvFileValues(fileName: String): Map<Double, Double> =
        javaClass.getResourceAsStream(fileName)?.bufferedReader().use { reader ->
            val res = mutableMapOf<Double, Double>()
            reader?.forEachLine { line ->
                val (x, y) = line.split(",").map { it.toDouble() }
                res[x] = y
            }
            return res
        }
}