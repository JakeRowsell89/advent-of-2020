package day8.part1

import java.io.File
import java.lang.Error

var ranInstructions = mutableListOf<Int>()

fun read(instructions: List<Instruction>, index: Int = 0, acc: Int = 0): Int {
    val instruction = instructions[index]

    if (ranInstructions.contains(index)) {
        return acc
    }

    ranInstructions.add(index)

    return when (instruction.operation) {
        Operation.ACC -> read(instructions, index + 1, acc + instruction.value)
        Operation.JMP -> read(instructions, index  + instruction.value, acc)
        Operation.NOP -> read(instructions, index + 1, acc)
        else -> {
            throw Error("Invalid operation provided")
        }
    }
}

enum class Operation {
    NOP, ACC, JMP
}

data class Instruction (val operation: Enum<Operation>, val value: Int)

fun makeInstruction(line: String): Instruction {
    val parts = line.split(" ")
    val operation = when (parts.first()) {
        "jmp" -> Operation.JMP
        "acc" -> Operation.ACC
        else -> Operation.NOP
    }
    return  Instruction(operation, parts.drop(1).first().toInt())
}

fun main() {
    val instructions = mutableListOf<Instruction>()

    File("src/day8/day-8-input").forEachLine {
        instructions.add(makeInstruction(it))
    }

    val result = read(instructions)

    println("Day 8 part 1: $result")
}