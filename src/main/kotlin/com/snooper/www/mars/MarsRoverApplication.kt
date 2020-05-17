package com.snooper.www.mars

import com.snooper.www.mars.model.CompassPoint
import com.snooper.www.mars.model.Instruction
import com.snooper.www.mars.model.Plateau
import com.snooper.www.mars.model.Position
import com.snooper.www.mars.service.MarsRoverService

fun main(args: Array<String>) {

    try {
        print("Enter Plateau Co-ordinates (in format X Y) > ")
        val inputPlateau = readLine()
        val plateau = readPlateau(inputPlateau)
        val marsRover = MarsRoverService(plateau)

        var done = false
        while (!done) {
            print("Enter Starting Position (in format X Y N|S|E|W) [Q to Quit] > ")
            val inputStart = readLine()
            val startPosition = readPosition(inputStart)

            if (startPosition != null) {
                print("Enter Instructions (in format LMR) > ")
                val inputInstruction = readLine()
                val instructions = readInstructions(inputInstruction)

                val finalPosition =  marsRover.getFinalPosition(startPosition, instructions)
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

private fun readInstructions(line: String?) : List<Instruction> {
    return line?.map { Instruction.valueOf(it.toString()) }.orEmpty()
}