package day1

import java.io.File

val SUM = 2020

fun getOddAndEvenPairsUnderSUM(fileName: String): Pair<List<Int>,List<Int>> {
    val odds = mutableListOf<Int>()
    val evens = mutableListOf<Int>()
    File(fileName).forEachLine {
        val n = it.toInt()
        if (n <= SUM) {
            if (n % 2 == 0) evens.add(n)
            else odds.add(n)
        }
    }
    return Pair(odds, evens)
}

fun findPairWithSumSUM(l: List<Int>): Pair<Int, Int>?{
    for (j in 0..l.count() - 2) {
        for (i in j + 1..l.count() - 1) {
            if ((l[j] + l[i]) == SUM) {
                return Pair(l[j], l[i])
            }
        }
    }

    return null
}

fun mainPart1(): Int? {
    // A much simpler solution would be to get the list, and for each item search for SUM - item in the rest of the list
    val (odds, evens) = getOddAndEvenPairsUnderSUM("src/day1/day-1-part-1-input")
    val oddPair: Pair<Int, Int>?  = findPairWithSumSUM(odds)
    val evenPair: Pair<Int, Int>?  = findPairWithSumSUM(evens)
    if (evenPair != null) {
        return evenPair.first * evenPair.second
    } else if (oddPair != null) {
        return oddPair.first * oddPair.second
    }
    return null
}

fun part1() {
    val result = mainPart1()
    println("Day 1 part 1: $result")
}