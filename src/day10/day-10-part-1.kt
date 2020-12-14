package day10.part1

import java.io.File

fun getDifferences(ar: List<Int>): Int {
    var prevInput = 0
    var dif1 = 0
    var dif3 = 1 // built in + 3
    for (i in 0 until ar.size) {
        val diff = ar[i] - prevInput

        if (diff === 1) {
            dif1++
        } else if (diff === 3) {
            dif3++
        }
        prevInput = ar[i]
    }

    return dif1 * dif3
}

fun main() {
    val allInputs = mutableListOf<Int>()

    File("src/day10/day-10-input").forEachLine {
        allInputs.add(it.toInt())
    }
    allInputs.sort()

    val result = getDifferences(allInputs)
    println("Day 10 part 1: $result")
}