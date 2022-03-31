import java.lang.StringBuilder

fun main() {
    val input = readInput("day14")
    println(day14Part1(input))
}

fun day14Part1(input: List<String>): Int{
    var template = input[0]
    val map = input.drop(2).map { it.split("->") }.associate { t -> t[0].trim() to t[1].trim() }
    val result = StringBuilder()
    repeat(10){
        result.clear().append("")
       for (i in 0 .. template.length -2 ){
           val toAdd =
               if (result.lastOrNull() !=  template[i] )
                "${template[i]}${map.getOrDefault("${template[i]}${template[i + 1]}", "")}${template[i + 1]}"
            else
                   "${map.getOrDefault("${template[i]}${template[i + 1]}", "")}${template[i + 1]}"
           result.append(toAdd)
        }
        template = result.toString()
    }
    val counts= result.map {
        (it to result.count {char-> char == it })
    }
    return counts.maxOf { it.second }  - counts.minOf { it.second }
}
