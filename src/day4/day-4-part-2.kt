package day4.part2

import java.io.File

val validEyeColours = listOf<String>("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

fun isValidHeight (s: String): Boolean {
    val amount = s.replace(Regex("(cm|in)$"), "").toInt()
    if (s.endsWith("cm")) {
        return amount >= 150 &&  amount <= 193
    } else if (s.endsWith("in")) {
        return amount >= 19 &&  amount <= 76
    } else {
        return false
    }
}

val requiredFields = listOf<Pair<String, (String) -> Boolean>>(
    Pair("byr", fun (s: String): Boolean { return s.length == 4 && s.toInt() >= 1920 && s.toInt() <= 2002}),// four digits; at least 1920 and at most 2002
    Pair("iyr", fun (s: String): Boolean { return s.length == 4 && s.toInt() >= 2010 && s.toInt() <= 2020}), // four digits; at least 2010 and at most 2020
    Pair("eyr", fun (s: String): Boolean { return s.length == 4 && s.toInt() >= 2020 && s.toInt() <= 2030}), // four digits; at least 2020 and at most 2030
    Pair("hgt", fun (s: String): Boolean { return isValidHeight(s)}),
    Pair("hcl", fun (s: String): Boolean { return Regex("^#[0-9a-f]{6}$").matches(s)}), //# followed by exactly six characters 0-9 or a-f.
    Pair("ecl", fun (s: String): Boolean { return validEyeColours.contains(s)}), // exactly one of: amb blu brn gry grn hzl oth.
    Pair("pid", fun (s: String): Boolean { return Regex("^[0-9]{9}$").matches(s)}) // a nine-digit number, including leading zeroes.
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
    val fields = passport.split(" ")

    return requiredFields.all{
        val field = it.first
        val hasValidFields = passport.contains(field + ":")

        if (hasValidFields) {
            val fieldResult = fields.find { it.startsWith(field) }?.split(":")

            if (fieldResult != null) {
                it.second(fieldResult[1])
            } else {
                false
            }
        } else {
            false
        }
    }
}

fun mainPart1(): Int {
    val scans = scanPassports("src/day4/day-4-input")
    return scans.filter { isValidPassport(it) }.count()
}

fun main() {
    val result = mainPart1()
    println("Day 4 part 2: $result")
}