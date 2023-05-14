package modules

interface SystemModule {
    val cot: (Double) -> Double
    val csc: (Double) -> Double
    val sin: (Double) -> Double
    val tan: (Double) -> Double
    val log: (Double, Double) -> Double
}
