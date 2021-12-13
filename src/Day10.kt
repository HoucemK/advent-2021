fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        for (i in input)
            result += analyse(i.map { Chunk(it) })
        return result
    }

    fun part2(input: List<String>): Long {
        var result = mutableListOf<Long?>()
        for (i in input.indices)
            analyse2(input[i].map { Chunk(it) }).let {
                if (it!= null && it > 0 ) result += it.toLong()
            }

        result.sortBy { it }
        println(result.toString())
        return result[result.size/2] ?: 0
    }

    val input = readInput("day10")
    println(part1(input))
    println(part2(input))
}

class Chunk(
    val char: Char
) {
    fun isOpener() =
        map.keys.contains(char)

    private val map = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}',
        '<' to '>'
    )
    private val mapScore = mapOf(
          ')' to 3,
          ']' to 57,
          '}' to 1197,
          '>' to 25137,
    )
    fun getTheCloser() : Char =
        map[char] ?: ' '
    fun errorScore() =
        mapScore[char] ?: 0

    override fun toString() =
        char.toString()
}

fun analyse(inputList: List<Chunk>): Int {
    val listBuff = mutableListOf<Chunk>()
    for (i in inputList) {
        when {
            i.isOpener() -> listBuff.add(i)
            listBuff.last().getTheCloser() == i.char -> listBuff.removeLast()
            else -> return i.errorScore()
        }
    }
    return 0

}

fun analyse2(inputList: List<Chunk>): Long? {
    val listBuff = mutableListOf<Chunk>()
    for (i in inputList) {
        when {
            i.isOpener() -> listBuff.add(i)
            listBuff.last().getTheCloser() == i.char -> listBuff.removeLast()
            else -> return null
        }
    }
    val missingChunk : List<Chunk> =  listBuff.asReversed().map { Chunk( it.getTheCloser() )}
    return missingChunk.toScore()
}

fun List<Chunk>.toScore(): Long {
    var result : Long = 0
    val mapScore = mapOf(
        ')' to 1,
        ']' to 2,
        '}' to 3,
        '>' to 4,)
    this.forEach {
        result = result * 5 + (mapScore[it.char ] ?: 0)
    }
    return result
}


