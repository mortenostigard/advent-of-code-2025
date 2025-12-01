fun rotatePosition(currentPosition: Int, direction: Char, steps: Int): Int = when (direction) {
    'R' -> (currentPosition + steps) % 100
    'L' -> (currentPosition - steps).mod(100)
    else -> currentPosition
}

fun countZeroCrossings(currentPosition: Int, direction: Char, steps: Int): Int = when (direction) {
    'R' -> (currentPosition + steps) / 100
    'L' -> when {
        currentPosition == 0 -> steps / 100
        steps >= currentPosition -> 1 + (steps - currentPosition) / 100
        else -> 0
    }
    else -> 0
}

fun main() {

    fun part1(input: List<String>): Int {
        var currentPosition = 50
        return input.count { instruction ->
            val direction = instruction.first()
            val steps = instruction.drop(1).toInt()
            currentPosition = rotatePosition(currentPosition, direction, steps)
            currentPosition == 0
        }
    }

    fun part2(input: List<String>): Int {
        var currentPosition = 50
        return input.sumOf { instruction ->
            val direction = instruction.first()
            val steps = instruction.drop(1).toInt()
            val crossings = countZeroCrossings(currentPosition, direction, steps)
            currentPosition = rotatePosition(currentPosition, direction, steps)
            crossings
        }
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
