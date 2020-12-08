package day8.part2

import java.io.File
import java.lang.Error

var ranInstructions = mutableListOf<Int>()

enum class Operation {
    NOP, ACC, JMP
}

data class Instruction (val operation: Enum<Operation>, val value: Int)

fun read(instructions: List<Instruction>, index: Int = 0, acc: Int = 0): Int {
    val instruction = instructions[index]

    if (ranInstructions.contains(index)) {
        return acc
    }

    if (index == (instructions.count() - 1)) {
        throw Error("$acc")
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

fun makeInstruction(line: String): Instruction {
    val parts = line.split(" ")
    val operation = when (parts.first()) {
        "jmp" -> Operation.JMP
        "acc" -> Operation.ACC
        else -> Operation.NOP
    }
    return Instruction(operation, parts.drop(1).first().toInt())
}

fun main() {
    val instructions = mutableListOf<Instruction>()

    File("src/day8/day-8-input").forEachLine {
        instructions.add(makeInstruction(it))
    }

    for (i in 0 until instructions.count()) {
        if (instructions[i].operation != Operation.ACC) {
            val newInstruct = listOf(Instruction(if (instructions[i].operation == Operation.JMP) Operation.NOP else Operation.JMP, instructions[i].value ))
            val newArray = listOf(
                instructions.slice(0..i - 1),
                newInstruct,
                instructions.slice(i+1..instructions.count()-1)
            ).flatten()

            try {
                ranInstructions.clear()
                read(newArray)
            } catch (e: Error) {
                val message = e.message
                println("Day 8 part 1: $message")
            }
        } else {
            continue
        }
    }
}