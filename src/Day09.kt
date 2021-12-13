
fun mainPart1() {
    val testInput = readInput("day09")
    val map: List<List<Int>> = testInput.map { it -> it.map { it.digitToInt() } }
    var result = 0
    for (i in map.indices) {
        for (j in map[i].indices) {
            val current = map[i][j]
            if ((current < getHigh(map, i - 1, j))
                && (current < getHigh(map, i, j - 1))
                && (current < getHigh(map, i, j + 1))
                && (current < getHigh(map, i + 1, j))
            ) {
                println("${i + 1} - ${j + 1} number ${map[i][j]} ")
                result += (map[i][j]) + 1
            }
        }
    }
    println(result)
}

fun main() {
    val testInput = readInput("day09")
    val map: List<List<Int>> = testInput.map { it -> it.map { it.digitToInt() } }
    val bigBasin = BigBasin()
    for (i in map.indices) {
        for (j in map[i].indices) {
            val basin = Basin.createBasin(map, i, j)
            bigBasin.addBasin(basin = basin)
        }
    }
    bigBasin.printResult()
}

fun getHigh(map: List<List<Int>>, i: Int, j: Int): Int =
    if ((i < 0) || (i >= map.size) || (j < 0) || (j >= map[i].size))
        0
    else
        map[i][j]

class Basin(
    val list: MutableList<Pair<Int, Int>> = mutableListOf(),
) {
    val size
        get() =list.size
    private fun addLocation(location: Pair<Int, Int>) {
        if(!haveThisLocation(location))
            this.list.add(location)
    }

    fun discoverTheBasin(map: List<List<Int>>, i: Int, j: Int) {
        val current = map[i][j]
        if (current < 9)
            addLocation(Pair(i,j))
        if (current < getHigh(map, i - 1, j))
            discoverTheBasin(map, i - 1, j)
        if (current < getHigh(map, i, j - 1))
            discoverTheBasin(map, i, j - 1)
        if (current < getHigh(map, i, j + 1))
            discoverTheBasin(map, i, j + 1)
        if (current < getHigh(map, i + 1, j))
            discoverTheBasin(map, i + 1, j)
    }

    companion object {
        fun createBasin(map: List<List<Int>>, i: Int, j: Int): Basin {
            val basin = Basin()
            basin.discoverTheBasin(map, i, j)
            return basin
        }
    }

    fun isBasinWithCommonLocation(basin: Basin): Boolean {
        for (i in basin.list)
            if (haveThisLocation(i))
                return true
        return false
    }

    private fun haveThisLocation(location: Pair<Int, Int>) =
        list.contains(location)

}


class BigBasin(
    private val list: MutableList<Basin> = mutableListOf()
) {
    fun addBasin(basin: Basin) {
        if (basin.list.size > 0) {
            for (i in list.indices) {
                if (list[i].isBasinWithCommonLocation(basin))
                     if (basin.list.size > list[i].size) {
                         list.removeAt(i)
                         list.add(basin)
                         return
                     }
            }
            list.add(basin)
        }
    }

    fun printResult() {
        list.sortByDescending { it.list.size }
        val final = list[0].list.size * list[1].list.size * list[2].list.size
        println(final)
    }

}

