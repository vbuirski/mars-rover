package com.snooper.www.mars.controller

import com.snooper.www.mars.common.CompassPoint
import com.snooper.www.mars.common.Instruction
import com.snooper.www.mars.common.Plateau
import com.snooper.www.mars.common.Position

class MarsRover(
        val plateau: Plateau,
        val startPosition: Position,
        val instructions: ArrayList<Instruction>) {

    var x = startPosition.x
    var y = startPosition.y
    var direction = startPosition.compassPoint

    fun turn(instruction: Instruction) : Position {
        when (direction) {
            CompassPoint.N -> direction = if (instruction === Instruction.L) CompassPoint.W else CompassPoint.E
            CompassPoint.S -> direction = if (instruction === Instruction.L) CompassPoint.E else CompassPoint.W
            CompassPoint.W ->  direction = if (instruction === Instruction.L) CompassPoint.S else CompassPoint.N
            CompassPoint.E -> direction = if (instruction === Instruction.L) CompassPoint.N else CompassPoint.S
        }
        return Position(x, y, direction)
    }

    fun move(direction: CompassPoint) : Position {
        if (direction === CompassPoint.N && isMoveAllowed(y+1, plateau.yBoundary)) {
            y++ }
        else if (direction === CompassPoint.S && isMoveAllowed(y-1, plateau.yBoundary)) {
            y-- }
        else if (direction === CompassPoint.E && isMoveAllowed(x+1, plateau.xBoundary)) {
            x++ }
        else if (direction === CompassPoint.W && isMoveAllowed(x-1, plateau.xBoundary)) {
            x-- }
        else {
            println("move not allowed")
        }
        return Position(x, y, direction)
    }

    fun isMoveAllowed(coordinate : Int, maxBoundary : Int) : Boolean {
        return if (coordinate >= 0 && coordinate <= maxBoundary) true else false
    }

    fun getFinalPosition(): Position {
        instructions.forEach {
            when(it) {
                Instruction.L -> turn(Instruction.L)
                Instruction.M -> move(direction)
                Instruction.R -> turn(Instruction.R)
            }
        }
        return Position(x, y, direction)
    }
}