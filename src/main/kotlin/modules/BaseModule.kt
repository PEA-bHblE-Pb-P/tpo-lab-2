package modules

interface BaseModule {
    val ln: (Double, Double) -> Double
    val cos: (Double, Double) -> Double
}