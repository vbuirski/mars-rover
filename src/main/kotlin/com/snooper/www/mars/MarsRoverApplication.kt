package com.snooper.www.mars

import com.snooper.www.mars.common.CompassPoint
import com.snooper.www.mars.common.Instruction
import com.snooper.www.mars.common.Plateau
import com.snooper.www.mars.common.Position
import com.snooper.www.mars.controller.MarsRover
import com.snooper.www.mars.util.FileUtil

fun main(args: Array<String>) {

    try {
        val lines = FileUtil.readFile("src/main/resources/marsrover.txt")
        val plateau = readPlateau(lines.first())

        var index = 1
        while (index < lines.size) {
            val startPosition = readPosition(lines[index++])
            val instructions = readInstructions(lines[index++])

            val marsRover = MarsRover(plateau, startPosition, instructions)
            val finalPosition = marsRover.getFinalPosition()
            println(finalPosition.x.toString().plus(" ").plus(finalPosition.y).plus(" ").plus(finalPosition.compassPoint))
        }
    } catch (e: NumberFormatException) {
        println("Invalid Input File - remove any missing lines")
    } catch (e: IllegalArgumentException) {
        println("Invalid Input File - verify list of instructions")
    } catch (e: IndexOutOfBoundsException) {
        println("Invalid Input File - verify each starting position has a corresponding list of instructions")
    }
}

private fun readPlateau(line: String): Plateau {
    var maxBoundary = line.split(" ").map { it.toInt() }
    return Plateau(maxBoundary[0], maxBoundary[1])
}

private fun readPosition(line: String) : Position {
    var position = line.split(" ")
    return Position(position[0].toInt(), position[1].toInt(), CompassPoint.valueOf(position[2]))
}

private fun readInstructions(line: String) : ArrayList<Instruction> {
    var instructions = ArrayList<Instruction>()
    line.forEach { instructions.add(Instruction.valueOf(it.toString())) }
    return instructions
}