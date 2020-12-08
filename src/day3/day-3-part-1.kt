package day3.part1

import java.io.File

val file = "src/day3/day-3-input"

fun getTreesOnPath(grid: MutableList<List<String>>) : Int {
    var trees = 0
    var pos = Pair(0, 0)
    var completed = false

    while (!completed) {
        if (grid[pos.second][pos.first] == "#") {
            trees += 1
        }

        pos = Pair(pos.first + 3, pos.second + 1)

        if (pos.second >= grid.count()) {
            completed = true
        } else if (pos.first >= grid[pos.second].count()) {
            val excess = pos.first - grid[pos.second].count()
            pos = Pair(0 + excess, pos.second)
        }
    }

    return trees
}

fun main() {
    var grid = mutableListOf<List<String>>()

    File(file).forEachLine {
        grid.add(it.trim().split("").drop(1).dropLast(1))
    }

    val result = getTreesOnPath(grid)

    println("Day 3 part 1: $result")
}