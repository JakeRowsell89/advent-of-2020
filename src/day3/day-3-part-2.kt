package day3.part2

import java.io.File

val file = "src/day3/day-3-input"

fun getTreesOnPath(grid: MutableList<List<String>>, offsetX: Int, offsetY: Int) : Int {
    var trees = 0
    var pos = Pair(0, 0)
    var completed = false
    while (!completed) {
        if (grid[pos.second][pos.first] == "#") {
            trees += 1
        }

        pos = Pair(pos.first + offsetX, pos.second + offsetY)

        if (pos.second >= grid.count()) {
            completed = true
        } else if (pos.first >= grid[pos.second].count()) {
            val excess = pos.first - grid[pos.second].count()
            pos = Pair(0 + excess, pos.second)
        }
    }
    return trees
}

fun mainPart2(): Long {
    var grid = mutableListOf<List<String>>()
    File(day3.part1.file).forEachLine {
        grid.add(it.trim().split("").drop(1).dropLast(1))
    }
    val routes = listOf(
        Pair(1,1),
        Pair(3, 1),
        Pair( 5, 1),
        Pair(7, 1),
        Pair(1, 2))

    return routes
        .map {
            getTreesOnPath(grid, it.first, it.second).toLong()
        }
        .reduce {
            acc, n -> acc * n
        }
}

fun main() {
    val result = mainPart2()
    println("Day 3 part 2: $result")
}