fun solve(input: String): Int =
    input
        .lines()
        .map { it.toInt() }
        .zipWithNext()
        .fold(0) { sum, pair ->
            val(current, next) = pair
            if(current < next) sum + 1 else sum
        }
