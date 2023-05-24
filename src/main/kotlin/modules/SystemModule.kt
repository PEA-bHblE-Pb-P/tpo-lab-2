package modules

interface SystemModule {
    val cot: (Double, Double) -> Double
    val sin: (Double, Double) -> Double
    val tan: (Double, Double) -> Double
    val log: (Double, Double, Double) -> Double
    val csc: (Double, Double) -> Double
    val system: (Double, Double) -> Double
}
