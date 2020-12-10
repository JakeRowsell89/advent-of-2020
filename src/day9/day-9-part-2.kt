package day9.part2

import java.io.File

data class Result(val found: Boolean = false, val value: Long)

fun findSequenceToSum(input: List<Long>, sum: Long): List<Long>? {
    // Refactor: This could work by using a sliding window where total exceeding sum means drop first, below sum is add next
    if (input.isEmpty()) {
        throw Error("No inputs found")
    }

    val seqEndIndex = input.foldIndexed(Result(false, 0)){ i, acc, v ->
        var x = acc
        if (acc.value === sum) {
            x = Result(true, i.toLong())
        } else if (!acc.found && acc.value !== (-1).toLong()) {
            x = Result(false, acc.value + v)
        } else if (!acc.found) {
            x = Result(false, -1)
        }
        x
    }

    if (!seqEndIndex.found) {
        return findSequenceToSum(input.drop(1), sum)
    } else {
        return input.slice(0 .. seqEndIndex.value.toInt())
    }
}

fun main() {
    val allInputs = mutableListOf<Long>()

    File("src/day9/day-9-input").forEachLine {
        allInputs.add(it.toLong())
    }

    val sequence = findSequenceToSum(allInputs, 36845998)
    val max = sequence?.max()
    val min = sequence?.min()
    val result = if (max != null && min != null) max+min else null

    println("Day 9 part 2: $result")
}