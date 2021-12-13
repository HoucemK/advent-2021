fun main() {
    val input = readInput("day13")
    println(printCode(input))
}

fun printCode(input: List<String>): Int {
    val set: MutableSet<Pair<Int, Int>> = input.map { it.split(",")}
        .map { xy: List<String> -> Pair(xy[0].toInt(),xy[1].toInt())}.toMutableSet()
    val newSet: MutableSet<Pair<Int, Int>> = mutableSetOf()
    val deleteSet: MutableSet<Pair<Int, Int>> = mutableSetOf()
    fun foldAlongY(x : Int){
        for (i in set)
        {
            if (i.second > x){
                newSet.add(Pair(i.first,2*x-(i.second)))
                deleteSet.add(i)
            }
        }
        for (i in newSet) set.add(i)
        for (i in deleteSet) set.remove(i)
    }
    fun foldAlongX(y : Int){
        for (i in set)
        {
            if (i.first > y){
                newSet.add(Pair((2*y-(i.first)),i.second))
                deleteSet.add(i)
            }
        }
        for (i in newSet) set.add(i)
        for (i in deleteSet) set.remove(i)
    }
    foldAlongX(655)
    foldAlongY(447)
    foldAlongX(327)
    foldAlongY(223)
    foldAlongX(163)
    foldAlongY(111)
    foldAlongX(81)
    foldAlongY(55)
    foldAlongX(40)
    foldAlongY(27)
    foldAlongY(13)
    foldAlongY(6)
    for (i in 0..set.maxOf { it.second }) {
        for (j in 0..set.maxOf { it.first }){
            if (set.contains(Pair(j,i)))
                print("#")
            else
                print(".")
        }
        println()
    }
    return set.count()
}