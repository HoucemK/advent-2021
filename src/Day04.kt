fun main() {
    val input = readInput("day04")
    val num = input[0].split(",").map { it.toInt() }
    val tables = input.asSequence().drop(2)
        .windowed(5, step = 6)
        .map {
            it.map { string ->
                string.trim().split(" ")
                    .filter { cc -> cc != "" }
                    .map { number -> number.toInt() }
            }
        }
        .map { data ->
            BingoCard(data)
        }.toList()

    fun part1Day04(): Int {
        for (nu in num) {
            tables.forEach {
                if (it.mark(nu))
                    return it.score
            }
        }
        return 0
    }

    fun part2Day04(): Int {
        var nu = 0
        while (tables.count { !it.isAWinner } > 1) {
            tables.forEach { it.mark(num[nu])}
            nu++
        }
        for (n in num) {
            tables.first() { !it.isAWinner }.let {
                if (it.mark(n))
                    return it.score
            }

        }
        return 0
    }
    println(part1Day04()) //23177
    println(part2Day04()) //6804

}

data class BingoCard(
    val data: List<List<Int>>,
) {
    var score = 0
    var isAWinner = false
    private val checked = Array(5) { BooleanArray(5) { false } }
    fun mark(n: Int): Boolean {
        data.forEachIndexed { i, list ->
            list.forEachIndexed { j, _ ->
                if (data[i][j] == n) {
                    checked[i][j] = true
                    if (checked[i].all { it }) {
                        updateScore(i, j)
                        isAWinner = true
                        return true
                    }
                    if (checked.map { list -> list[j] }.all { it }) {
                        updateScore(i, j)
                        isAWinner = true
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun updateScore(i: Int, j: Int) {
        for (ii in 0..4) for (jj in 0..4)
            if (!checked[ii][jj])
                score += data[ii][jj]
        score *= data[i][j]
    }
}