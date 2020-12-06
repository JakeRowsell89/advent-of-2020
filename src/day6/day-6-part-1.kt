package day6.part1

import java.io.File

fun mainPart1(): Int {
    return File("src/day6/day-6-input")
        .readText()
        .split("\n\n")
        .map{
            it.replace("\n", "")
                .split("")
                .distinct()
                .drop(1) // drop empty string
        }
        .fold(0){acc, v -> v.count() + acc }
}

fun main() {
    val result = mainPart1()
    println("Day 6 part 1: $result")
}