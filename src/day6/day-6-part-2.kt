package day6.part2

import java.io.File
//
//function allAnsweredYes (arr) {
//    if (arr.length === 1) {
//        return arr[0]
//    }
//    return findElementsInAllSubArrays(arr)
//}
//
//function findElementsInAllSubArrays(arr) {
//    const allPotentialValues = Array.from(new Set(arr.reduce((acc, val) => acc.concat(val), [])))
//
//    return allPotentialValues.filter(e => arr.every(a => a.find(x => x === e)))
//}

fun main() {
    val result = File("src/day6/day-6-input")
        .readText()
        .split("\n\n")
        .map{
            it.replace("\n", "")
                .split("")
                .distinct()
//                .map(g => g.split('\n'))
//            .map(g => g.map(x => x.split('')))
//            .map(allAnsweredYes)
//            .reduce((acc, v) => acc + v.length,0)
                .drop(1) // drop empty string
        }
        .fold(0){acc, v -> v.count() + acc }




    println("Day 6 part 2: $result")
}