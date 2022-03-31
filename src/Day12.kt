fun main() {

    val input = readInput("test_input")
    println(part1(input))
}


fun part1(input: List<String>): Int {
    val connections: List<List<String>> = input.map { it.split("-") }
    val g = HashMap<String,HashSet<String>>()
    for (s in input) {
        val (a, b) = s.split("-")
        g.getOrPut(a) { HashSet() }.add(b)
        g.getOrPut(b) { HashSet() }.add(a)
    }
    fun findNextConnection(path: List<String>): List<List<String>> {
        path.map { (it[0] to it[1]) }
        val r = mutableListOf<List<String>>()
        connections.map {
            if (it[0] == path.last())
                r.add(path + it[1])
        }
//        connections.map {
//            if (it[0] == path.last())
//                r.add(path + it[1])
//        }
//        r.forEach() {
//            if (it.last() != "end"){
//                findNextConnection(it)
//            }else
//                r
//        }
        return r
    }
//    val paths = connections
//        .filter { it[0] == "start" }
//        .map {
//                findNextConnection(it)
//        }
//    paths.forEach {
//        it.forEach {c-> print("$c,") }
//
//    }
    print(g.toString())
    return 0
}