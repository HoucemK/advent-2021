
fun main() {
    val input = readInput("day05")
    val lines = input.map {
        it.trim().split("->")
            .map { points -> points.trim().split(",") }
            .map { p -> Pair(p[0].toInt(), p[1].toInt()) }
    }

    fun part1Day05(): Int {
        fun getPoints(p1: Pair<Int, Int>, p2: Pair<Int, Int>): List<Pair<Int, Int>> {
            val list = mutableListOf<Pair<Int, Int>>()
            if (p1.first == p2.first)
                for (i in minOf(p1.second, p2.second)..maxOf(p1.second, p2.second))
                    list.add(Pair(p1.first, i))
            if (p1.second == p2.second)
                for (i in minOf(p1.first, p2.first)..maxOf(p1.first, p2.first))
                    list.add(Pair(i, p1.second))
            return list
        }
        return lines.flatMap { getPoints(it[0], it[1]) }.groupingBy { it }
            .eachCount()
            .filterValues { v -> v > 1 }.count()
    }

    fun part2Day05(): Int {
        fun getPoints2(p1: Pair<Int, Int>, p2: Pair<Int, Int>): List<Pair<Int, Int>> {
            val list = mutableListOf<Pair<Int, Int>>()
            val (x1, y1) = p1
            val (x2, y2) = p2
            when {
                (x1 == x2) -> {
                    for (i in minOf(y1, y2)..maxOf(y1, y2))
                        list.add(Pair(x1, i))
                }

                (y1 == y2) -> {
                    for (i in minOf(x1, x2)..maxOf(x1, x2))
                        list.add(Pair(i, y2))
                }

                else -> {
                    val incX = if(x1>x2) -1 else 1
                    val incY = if(y1>y2) -1 else 1
                    var xx = x1
                    var yy = y1
                    for (i in minOf(x2, x1)..maxOf(x1, x2)){
                        list.add(Pair(xx, yy))
                        xx += incX
                        yy += incY
                    }
                }
            }
                return list
            }
            return lines.flatMap { getPoints2(it[0], it[1]) }.groupingBy { it }
                .eachCount()
                .filterValues { v -> v > 1 }.count()
        }
        println(part1Day05()) //5124
        println(part2Day05()) //19771
    }
