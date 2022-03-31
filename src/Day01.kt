fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        val v = input.map { it.toInt() }
        v.forEachIndexed { index, i ->
            if(index != 0){
                if (i> v[index -1] ) result ++
            }
        }
        return result
    }

    fun part2(input: List<String>): Int =
        input.map { it.toInt() }
            .windowed(3)
            .windowed(2)
            .count { (a,b) ->  a.sum() < b.sum() }
    
    val input = readInput("day01")
    println(part1(input))
    println(part2(input))
}
