package day9.part1

import java.io.File

fun validate(list: List<Long>, x: Long): Boolean {
    return list.fold(false) { acc: Boolean, n: Long -> acc || list.contains(x - n) }
}

fun validateInputs(allInputs: List<Long>, preamble: Int, index: Int = 0): Long {
    if ((index + preamble) < allInputs.size) {
        if (validate(allInputs.slice(index .. preamble + index), allInputs[index + preamble])) {
            return validateInputs(allInputs, preamble, index + 1)
        } else {
            return allInputs[index + preamble]
        }
    }
    return -1
}


fun main() {
    val allInputs = mutableListOf<Long>()

    File("src/day9/day-9-input").forEachLine {
        allInputs.add(it.toLong())
    }

    val result = validateInputs(allInputs, 25)

    println("Day 9 part 1: $result")
}