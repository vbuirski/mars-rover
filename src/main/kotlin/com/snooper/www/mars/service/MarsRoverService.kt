package com.snooper.www.mars.service

import com.snooper.www.mars.model.CompassPoint
import com.snooper.www.mars.model.Instruction
import com.snooper.www.mars.model.Plateau
import com.snooper.www.mars.model.Position

class MarsRoverService(private val plateau: Plateau) {

    fun turn(position: Position, instruction: Instruction) : Position {
        val direction = when (position.compassPoint) {
            CompassPoint.N -> if (instruction == Instruction.L) CompassPoint.W else CompassPoint.E
            CompassPoint.S -> if (instruction == Instruction.L) CompassPoint.E else CompassPoint.W
            CompassPoint.W -> if (instruction == Instruction.L) CompassPoint.S else CompassPoint.N
            CompassPoint.E -> if (instruction == Instruction.L) CompassPoint.N else CompassPoint.S
        }
        return Position(position.x, position.y, direction)
    }

    fun move(position: Position) : Position {
        var x = position.x
        var y = position.y
        if (position.compassPoint == CompassPoint.N && isMoveAllowed(y+1, plateau.yBoundary)) {
            y++ }
        else if (position.compassPoint == CompassPoint.S && isMoveAllowed(y-1, plateau.yBoundary)) {
            y-- }
        else if (position.compassPoint == CompassPoint.E && isMoveAllowed(x+1, plateau.xBoundary)) {
            x++ }
        else if (position.compassPoint == CompassPoint.W && isMoveAllowed(x-1, plateau.xBoundary)) {
            x-- }
        else {
            println("move not allowed")
        }
        return Position(x, y, position.compassPoint)
    }

    fun isMoveAllowed(coordinate : Int, maxBoundary : Int) = coordinate in 0..maxBoundary

    fun getFinalPosition(startPosition: Position, instructions: List<Instruction>): Position {
        var position = startPosition
        instructions.forEach {
            position = when(it) {
                Instruction.L -> turn(position, Instruction.L)
                Instruction.M -> move(position)
                Instruction.R -> turn(position, Instruction.R)
            }
        }
        return position
    }
}