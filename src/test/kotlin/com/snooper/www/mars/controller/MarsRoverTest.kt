package com.snooper.www.mars.controller

import com.snooper.www.mars.model.CompassPoint
import com.snooper.www.mars.model.Instruction
import com.snooper.www.mars.model.Plateau
import com.snooper.www.mars.model.Position
import com.snooper.www.mars.service.MarsRoverService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MarsRoverTest {
    private val plateau = Plateau(5, 5)

    private lateinit var marsRoverService: MarsRoverService

    @BeforeEach
    fun setUp() {
        marsRoverService = MarsRoverService(plateau)
    }

    @Test
    fun whenMovingNorthThenYIsIncremented() {
        val position = Position(1, 1, CompassPoint.N)
        val newPosition = marsRoverService.move(position)
        Assertions.assertEquals(2, newPosition.y)
    }

    @Test
    fun whenMovingSouthThenYIsDecremented() {
        val position = Position(1, 1, CompassPoint.S)
        val newPosition = marsRoverService.move(position)
        Assertions.assertEquals(0, newPosition.y)
    }

    @Test
    fun whenMovingEastThenXIsIncremented() {
        val position = Position(1, 1, CompassPoint.E)
        val newPosition = marsRoverService.move(position)
        Assertions.assertEquals(2, newPosition.x)
    }

    @Test
    fun whenMovingWestThenXIsDecremented() {
        val position = Position(1, 1, CompassPoint.W)
        val newPosition = marsRoverService.move(position)
        Assertions.assertEquals(0, newPosition.x)
    }

    @Test
    fun whenTurningLeftFromNorthThenFaceWest() {
        val position = Position(1, 1, CompassPoint.N)
        val newPosition = marsRoverService.turn(position, Instruction.L)
        Assertions.assertEquals(CompassPoint.W, newPosition.compassPoint)
    }

    @Test
    fun whenTurningRightFromNorthThenFaceEast() {
        val position = Position(1, 1, CompassPoint.N)
        val newPosition = marsRoverService.turn(position, Instruction.L)
        Assertions.assertEquals(CompassPoint.W, newPosition.compassPoint)
    }

    @Test
    fun whenTurningLeftFromSouthThenFaceEast() {
        val position = Position(1, 1, CompassPoint.S)
        val newPosition = marsRoverService.turn(position, Instruction.L)
        Assertions.assertEquals(CompassPoint.E, newPosition.compassPoint)
    }

    @Test
    fun whenTurningRightFromSouthThenFaceWest() {
        val position = Position(1, 1, CompassPoint.S)
        val newPosition = marsRoverService.turn(position, Instruction.R)
        Assertions.assertEquals(CompassPoint.W, newPosition.compassPoint)
    }

    @Test
    fun whenTurningLeftFromEastThenFaceNorth() {
        val position = Position(1, 1, CompassPoint.E)
        val newPosition = marsRoverService.turn(position, Instruction.L)
        Assertions.assertEquals(CompassPoint.N, newPosition.compassPoint)
    }

    @Test
    fun whenTurningRightFromEastThenFaceSouth() {
        val position = Position(1, 1, CompassPoint.E)
        val newPosition = marsRoverService.turn(position, Instruction.R)
        Assertions.assertEquals(CompassPoint.S, newPosition.compassPoint)
    }

    @Test
    fun whenTurningLeftFromWestThenFaceSouth() {
        val position = Position(1, 1, CompassPoint.W)
        val newPosition = marsRoverService.turn(position, Instruction.L)
        Assertions.assertEquals(CompassPoint.S, newPosition.compassPoint)
    }

    @Test
    fun whenTurningRightFromWestThenFaceNorth() {
        val position = Position(1, 1, CompassPoint.W)
        val newPosition = marsRoverService.turn(position, Instruction.R)
        Assertions.assertEquals(CompassPoint.N, newPosition.compassPoint)
    }

    @Test
    fun whenCoordinatesAreInRangeThenAllowMove() {
        val isMoveAllowed = marsRoverService.isMoveAllowed(1, 2)
        Assertions.assertEquals(true, isMoveAllowed)
    }

    @Test
    fun whenCoordinatesAreGreaterThanBoundaryThenDoNotAllowMove() {
        val isMoveAllowed = marsRoverService.isMoveAllowed(2, 1)
        Assertions.assertEquals(false, isMoveAllowed)
    }

    @Test
    fun whenGivenInstructionsThenGetFinalPosition() {
        val instructions = arrayListOf<Instruction>(
                Instruction.L,
                Instruction.M,
                Instruction.L,
                Instruction.M,
                Instruction.L,
                Instruction.M,
                Instruction.L,
                Instruction.M,
                Instruction.M
        )
        val startPosition = Position(1, 2, CompassPoint.N)
        val expectedFinalPosition = Position(1, 3, CompassPoint.N)

        val finalPosition = marsRoverService.getFinalPosition(startPosition, instructions)

        Assertions.assertEquals(expectedFinalPosition.x, finalPosition.x)
        Assertions.assertEquals(expectedFinalPosition.y, finalPosition.y)
        Assertions.assertEquals(expectedFinalPosition.compassPoint, finalPosition.compassPoint)
    }
}