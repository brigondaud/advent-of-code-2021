fun solve(input: String): Int {
    val lines = input
        .lines()

    val rateSize = lines.first().length

    val binaryGammaRate = (0 until rateSize).fold("") { rate, index ->
        rate + lines
            .map { it[index] }
            .groupBy { it }
            .mapValues { it.value.size }
            .maxByOrNull { it.value }
            ?.key
    }

    val binaryEpsilonRate = binaryGammaRate.map { (1 - it.digitToInt()) }.joinToString("")


    val gammaRate = Integer.parseInt(binaryGammaRate, 2)
    val epsilonRate = Integer.parseInt(binaryEpsilonRate, 2)
    
    return gammaRate * epsilonRate
}
