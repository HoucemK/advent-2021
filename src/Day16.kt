import java.lang.StringBuilder

fun main() {
    val input = readInput("test_input")
    println(day16Part1(input))
}

fun day16Part1(input: List<String>): Int {
    val i  = "EE00D40C823060"
    val bytes = i.map {
        it.digitToInt(16).toString(2).padStart(4, '0')
    }.joinToString("")
    print(bytes)
    return 0
}

