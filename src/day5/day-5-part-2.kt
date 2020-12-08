package day5.part2

import java.io.File

fun getVal(str: String): Int {
    var binaryValue = str
        .replace(Regex("[FL]"), "0")
        .replace(Regex("[RB]"), "1")
        .toInt(2)

    var row = binaryValue / 8
    var col = binaryValue % 8

    return (row * 8) + col
}

fun main() {
    var binaryLines = mutableListOf<Int>()

    File("src/day5/day-5-input").forEachLine {
        binaryLines.add(getVal(it))
    }

    val passBeforeMine = binaryLines.find { binaryLines.contains(it + 2) && !binaryLines.contains(it + 1) }
    val result =  if (passBeforeMine == null) null else passBeforeMine + 1

    println("Day 5 part 2: $result")
}