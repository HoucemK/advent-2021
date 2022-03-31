fun main() {
    val input = readInput("test_input")
    println(day17Part1())
}

fun day17Part1(): Int {
    fun getTheDrop(startX: Int, startY: Int): Int {
        var result = Pair(0, 0)
        var max = -89
        var vx = startX
        var vy = startY
        while (result.first <= 160 && result.second >= -88) {
            result = Pair(
                result.first + vx,
                result.second +vy)
            
            if (vx > 0) vx--
            vy--
            }
            if ((result.first in 128..160) && (result.second in -142..-88) && (result.second > max) )
                max =  result.second
        return max
    }
    var mm = -888
    for (i in  1..1000 )
        for (j in 0..1000) {
            val nn = getTheDrop(i, j)
            if ( nn > -89 )
                mm = maxOf(mm,nn)
        }
    return mm
}