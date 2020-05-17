package com.snooper.www.mars

import com.snooper.www.mars.common.CompassPoint
import com.snooper.www.mars.common.Instruction
import com.snooper.www.mars.common.Plateau
import com.snooper.www.mars.common.Position
import com.snooper.www.mars.controller.MarsRover

fun main(args: Array<String>) {

    try {
        print("Enter Plateau Co-ordinates (in format X Y) > ")
        val inputPlateau = readLine()
        val plateau = readPlateau(inputPlateau)
        var finalPositions = ArrayList<Position>()

        var done = false
        while (!done) {
            print("Enter Starting Position (in format X Y N|S|E|W) [Q to Quit] > ")
            val inputStart = readLine()
            val startPosition = readPosition(inputStart)

            if (startPosition != null) {
                print("Enter Instructions (in format LMR) > ")
                val inputInstruction = readLine()
                val instructions = readInstructions(inputInstruction)

                if (instructions == null) {
                    return
                }
                val marsRover = MarsRover(plateau, startPosition, instructions)
                val finalPosition =  marsRover.getFinalPosition()
                finalPositions.add(finalPosition)
                print("The Final Position is: "  + finalPosition.x.toString().plus(" ").plus(finalPosition.y).plus(" ").plus(finalPosition.compassPoint).plus("\n"))
            } else {
                done = true
                println("Thank you, goodbye.")
            }
        }
    } catch (e: NumberFormatException) {
        println("Invalid Input [" + e.message + "]")
    } catch (e: IllegalArgumentException) {
        println("Invalid Input [" + e.message + "]")
    } catch (e: IndexOutOfBoundsException) {
        println("Invalid Input [" + e.message + "]")
    } catch (e: Exception) {
        println("General Exception [" + e.message + "]")
    }
}

private fun readPlateau(line: String?): Plateau {
    if (line != null) {
        var maxBoundary = line.split(" ").map { it.toInt() }
        return Plateau(maxBoundary[0], maxBoundary[1])
    }
    return Plateau(5, 5)
}

private fun readPosition(line: String?) : Position? {
    if (line != null && line != "Q") {
        var position = line.split(" ")
        return Position(position[0].toInt(), position[1].toInt(), CompassPoint.valueOf(position[2]))
    }
    return null
}

private fun readInstructions(line: String?) : ArrayList<Instruction>? {
    var instructions = ArrayList<Instruction>()
    if (line != null) {
        line.forEach { instructions.add(Instruction.valueOf(it.toString())) }
        return instructions
    }
    return null
}