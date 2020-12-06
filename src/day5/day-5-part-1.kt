package day5.part1

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

fun mainPart1(): Int {
    var binaryLines = mutableListOf<Int>()
    File("src/day5/day-5-input").forEachLine {
            binaryLines.add(getVal(it))
    }
    return binaryLines.reduce {acc, current -> if (acc > current) acc else current}
}

fun main() {
    val result = mainPart1()
    println("Day 5 part 1: $result")
}