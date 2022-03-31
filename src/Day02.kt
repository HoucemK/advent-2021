fun main() {
    fun part1Day2(input: List<String>): Int {
        var d = 0
        var c = 0
        input.map { it.split(" ") }
            .map { s -> s[0] to s[1].toInt() }
            .forEach { (a, b) ->
                when (a) {
                    "forward" -> c += b
                    "down" -> d += b
                    "up" -> d -= b
                }
            }
        return c * d
    }

    fun part2Day2(input: List<String>): Int {
        var d = 0
        var c = 0
        var aim = 0
        input.map { it.split(" ") }
            .map { s -> s[0] to s[1].toInt() }
            .forEach { (a, b) ->
                when (a) {
                    "forward" -> {
                        c += b
                        d += aim * b
                    }
                    "down" -> { aim += b }
                    "up" -> { aim -= b }
                }
            }
        return c * d
    }
    val input = readInput("day02")
    println(part1Day2(input))
    println(part2Day2(input))
}