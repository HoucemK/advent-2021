fun main() {
    fun part1Day03(input: List<String>): Int {
        val n = input[0].length
        val a0 = IntArray(n)
        val a1 = IntArray(n)
        for (i in input[0].indices) {
            a0[i] = input.map { it[i] }.groupingBy { it }.eachCount()['0'] ?: 0
            a1[i] = input.map { it[i] }.groupingBy { it }.eachCount()['1'] ?: 0
        }
        val g = CharArray(n) { '0' }
        val e = CharArray(n) { '0' }
        for (i in 0 until n) {
            if (a0[i] > a1[i]) e[i] = '1' else g[i] = '1'
        }
        return g.concatToString().toInt(2) * e.concatToString().toInt(2)
    }

    fun part2Day03(input: List<String>): Int {
        var result = input
        while (result.size > 1){
            for ( i in input[0].indices) {
                if (result.map { it[i] }.groupingBy { it }.eachCount()['0'] ?: 0 >
                    result.map { it[i] }.groupingBy { it }.eachCount()['1'] ?: 0 )
                      result = result.filter { it[i] == '0' }
                else
                    result = result.filter { it[i] == '1' }
            }
        }
            return result.first().toInt(2) /** e.concatToString().toInt(2)*/
    }


    val input = readInput("day03")
    println(part1Day03(input))
//    println(part2(input))
}