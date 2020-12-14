package day10.part2

import java.io.File

fun getDifferences(ar: List<Long>, cache: HashMap<Int, Long>, index: Int = 0): Long {
    val nextOptions = ar.foldIndexed(listOf<Int>()){ i, acc, v -> if (i >  index && v - ar[index] <= 3) acc + i else acc }

    if (nextOptions.isEmpty() || index === ar.size) {
        return 1
    } else {
        return nextOptions.fold(0) { acc: Long, v: Int ->
            var value = cache.get(v)
            if (cache.get(v) == null) {
                var newValue = getDifferences(ar, cache, v)
                cache.set(v, newValue)
                value = newValue
            }
            value!!.plus(acc)
        }
    }
}

fun main() {
    val allInputs = mutableListOf<Long>(0)

    File("src/day10/day-10-input").forEachLine {
        allInputs.add(it.toLong())
    }
    allInputs.sort()

    val result = getDifferences(allInputs, HashMap<Int, Long>())
    println("Day 10 part 1: $result")
}