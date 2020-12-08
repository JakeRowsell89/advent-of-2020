package day4.part1

import java.io.File

val requiredFields = listOf<String>(
    "byr",
    "iyr",
    "eyr",
    "hgt",
    "hcl",
    "ecl",
    "pid"
)

fun scanPassports(fileName: String): List<String> {
    var cache = ""
    var passports = mutableListOf<String>()
    File(fileName).forEachLine {
        if (it == "") {
            passports.add(cache)
            cache = ""
        } else {
            cache += " " + it
        }
    }
    passports.add(cache)
    return passports
}

fun isValidPassport(passport: String): Boolean {
    return requiredFields.all{ passport.toLowerCase().contains(it + ":") }
}

fun main() {
    val scans = scanPassports("src/day4/day-4-input")
    val result = scans.filter { isValidPassport(it) }.count()

    println("Day 4 part 1: $result")
}