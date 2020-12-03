package day2.part1

import java.io.File

val file = "src/day2/day-3-input"

fun isValidRow(row: String): Boolean {
    val fields = row
            .replace(":", "")
            .replace("-", ",")
            .split(" ")
    val amount = fields[0]
    val characters = fields[1]
    val input = String(
            fields[2]
                    .replace(Regex("[^" + characters + "]"), "")
                    .toCharArray()
                    .apply { sort() }
    )

    // .map fixes a bug for multiple characters, however not required as only a single character used for all test data
    val reg = characters.map{  "[" + it + "]{" + amount + "}" } .joinToString("")

    return Regex(reg).matches(input)
}

fun mainPart1(): Int {
    var count = 0
    File(file).forEachLine {
        if (isValidRow(it)) {
            count += 1
        }
    }
    return count
}

fun main() {
    val result = mainPart1()
    println("Day 2 part 1: $result")
}