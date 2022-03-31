fun main2() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    val input = readIntInput("test_input")
    println(part1(input))
}

fun part1(input: List<List<Int>>): Int {
    val octopusMatrix: List<List<Octopus>> = input.indices.map { i ->
        input[i].indices.map { j ->
            Octopus(Pair(i, j), input[i][j], input)
        }
    }
    var result = 0
    for (i in 1..1) {
        octopusMatrix.forEach {
            it.forEach { octopus ->
                octopus.addEnergy()
                print(octopus.energy)
            }
            println()
        }

        octopusMatrix.forEach {
            it.map { octopus ->
                octopus.addEnergyIfAdjacent(octopusMatrix)
            }
        }

        println("----")
        octopusMatrix.forEach {
            it.forEach { octopus ->
                print(octopus.energy)
                if (octopus.energy == 0)
                    result++
            }
            println()
        }
        println("----")
    }
    return result
}

class Octopus(
    val location: Pair<Int, Int>,
    var energy: Int,
    input: List<List<Int>> = listOf(),
) {
    private var adjacents: List<Octopus> = listOf()

    init {
        addAdjacents(input)
    }

    fun addEnergy() {
        if (energy == 9)
            energy = 0
        else
            energy++
    }

    fun addEnergyIfAdjacent(octopusMatrix: List<List<Octopus>>) {
        adjacents.forEach {
            if (octopusMatrix[it.location.first][it.location.second].energy == 0) {
                if (energy != 0) {
                    addEnergy()
                }
            }
        }
    }

    private fun addAdjacents(input: List<List<Int>>) {
        val i = location.first
        val j = location.second
        val result = mutableListOf<Octopus>()
        if (input.isNotEmpty()) {
            for (ic in i - 1..i + 1) {
                for (jc in j - 1..j + 1)
                    if (i != ic || j != jc) {
                        if ((ic in input.indices) && (jc in input[ic].indices))
                            result += Octopus(Pair(ic, jc), input[ic][jc])
                    }
            }
        }
        this.adjacents = result
    }
}


fun main() {
    val input = readInput("day11")
    val a = input.map { it.map { it.digitToInt() }.toMutableList() }
    val n = a.size
    val m = a[0].size
    var ans = 0
    data class Pos(val i: Int, val j: Int)
    repeat(100) {
        val q = ArrayList<Pos>()
        fun inc(i: Int, j: Int) {
            a[i][j]++
            if (a[i][j] == 10) {
                q += Pos(i, j)
            }
        }
        for (i in 0 until n) for (j in 0 until m) inc(i, j)
        var qh = 0
        while (qh < q.size) {
            val (i, j) = q[qh++]
            for (di in -1..1) for(dj in -1..1) if (di != 0 || dj != 0) {
                val i1 = i + di
                val j1 = j + dj
                if (i1 in 0 until n && j1 in 0 until m) inc(i1, j1)
            }
        }
        ans += q.size
        for (p in q) {
            a[p.i][p.j] = 0
        }
    }
    println(ans)
}
