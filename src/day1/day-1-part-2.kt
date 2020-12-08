package day1.part2

import java.io.File

val SUM = 2020

fun getNumbersUnderSUM(fileName: String): List<Int> {
    val numbers = mutableListOf<Int>()
    File(fileName).forEachLine {
        val n = it.toInt()
        if (n <= SUM) {
            numbers.add(n)
        }
    }
    return numbers
}

fun findTripletWithSumSUM(l: List<Int>): Int?{
    for (k in 0..l.count() - 3) {
        for (j in k..l.count() - 2) {
            for (i in j + 1..l.count() - 1) {
                if ((l[j] + l[i]) + l[k] == SUM) {
                    return l[j] * l[i] * l[k]
                }
            }
        }
    }

    return null
}

fun main() {
    var result: Int? = null
    val numbers = getNumbersUnderSUM("src/day1/day-1-input")
    val triplet: Int?  = findTripletWithSumSUM(numbers)
    if (triplet != null) {
        result = triplet
    }

    println("Day 1 part 2: $result")
}