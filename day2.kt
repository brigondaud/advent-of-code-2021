
data class Position(val depth: Int, val horizontal: Int) {
    fun move(to: Direction, value: Int): Position =
        Position(
            depth = depth + value * to.depthCoeff(),
            horizontal = horizontal + value * to.horizontalCoeff()
        )

    companion object {
        val initial = Position(depth = 0, horizontal = 0)
    }
}

interface Direction {
    fun depthCoeff(): Int
    fun horizontalCoeff(): Int

    companion object {
        fun create(type: String): Pair<Direction, Int> = type.split(" ").let {
            Pair(
                when (it[0]) {
                    "forward" -> object : Direction {
                        override fun depthCoeff(): Int = 0
                        override fun horizontalCoeff(): Int = 1
                    }
                    "down" -> object : Direction {
                        override fun depthCoeff(): Int = 1
                        override fun horizontalCoeff(): Int = 0
                    }
                    "up" -> object : Direction {
                        override fun depthCoeff(): Int = -1
                        override fun horizontalCoeff(): Int = 0
                    }
                    else -> throw InvalidParameterException(type)
                },
                it[1].toInt()
            )
        }
    }
}

fun solve(input: String): Int =
    input
        .lines()
        .fold(Position.initial) { currentPosition, course ->
            val (direction, value) = Direction.create(course)
            currentPosition.move(to = direction, value = value)
        }.let {
            it.depth * it.horizontal
        }
