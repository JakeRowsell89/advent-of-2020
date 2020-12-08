package day6.part1

import java.io.File

fun main() {
    val result = File("src/day6/day-6-input")
        .readText()
        .split("\n\n")
        .map{
            it.replace("\n", "")
                .split("")
                .distinct()
                .drop(1) // drop empty string
        }
        .fold(0){acc, v -> v.count() + acc }
    println("Day 6 part 1: $result")
}