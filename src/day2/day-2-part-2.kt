package day2.part2

import java.io.File

val file = "src/day2/day-2-input"

fun isValidRow(row: String): Boolean {
    val data = row
        .replace(":", "")
        .split(" ")

    val positions = data[0].split("-").map{ it.toInt() }
    val character = data[1]
    val password = data[2]

    return positions.map{ password[it - 1] == character[0] }.filter { it }.size == 1
}

fun main() {
    var result = 0

    File(file).forEachLine {
        if (isValidRow(it)) {
            result += 1
        }
    }

    println("Day 2 part 2: $result")
}