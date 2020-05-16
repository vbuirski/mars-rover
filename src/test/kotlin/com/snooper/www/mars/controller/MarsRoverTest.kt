package test.kotlin.com.snooper.www.mars.controller

import com.snooper.www.mars.common.CompassPoint
import com.snooper.www.mars.common.Instruction
import com.snooper.www.mars.common.Plateau
import com.snooper.www.mars.common.Position
import com.snooper.www.mars.controller.MarsRover
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class MarsRoverTest {
    val plateau = Plateau(5, 5)
    val startPosition = Position(1, 2, CompassPoint.N)
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

    private var marsRover = MarsRover(plateau, startPosition, instructions)

    @BeforeEach
    fun initEach() {
        val startPosition = Position(1, 1, CompassPoint.N)
        marsRover = MarsRover(plateau, startPosition, instructions)
    }

    @Test
    fun whenMovingNorthThenYIsIncremented() {
        val newPosition = marsRover.move(CompassPoint.N)
        Assertions.assertEquals(2, newPosition.y)
    }

    @Test
    fun whenMovingSouthThenYIsDecremented() {
        val newPosition = marsRover.move(CompassPoint.S)
        Assertions.assertEquals(0, newPosition.y)
    }

    @Test
    fun whenMovingEastThenXIsIncremented() {
        val newPosition = marsRover.move(CompassPoint.E)
        Assertions.assertEquals(2, newPosition.x)
    }

    @Test
    fun whenMovingWestThenXIsDecremented() {
        val newPosition = marsRover.move(CompassPoint.W)
        Assertions.assertEquals(0, newPosition.x)
    }

    @Test
    fun whenTurningLeftFromNorthThenFaceWest() {
        val newPosition = marsRover.turn(Instruction.L)
        Assertions.assertEquals(CompassPoint.W, newPosition.compassPoint)
    }

    @Test
    fun whenTurningRightFromNorthThenFaceEast() {
        val newPosition = marsRover.turn(Instruction.L)
        Assertions.assertEquals(CompassPoint.W, newPosition.compassPoint)
    }

    @Test
    fun whenTurningLeftFromSouthThenFaceEast() {

        val startPosition = Position(1, 1, CompassPoint.S)
        marsRover = MarsRover(plateau, startPosition, instructions)

        val newPosition = marsRover.turn(Instruction.L)
        Assertions.assertEquals(CompassPoint.E, newPosition.compassPoint)
    }

    @Test
    fun whenTurningRightFromSouthThenFaceWest() {

        val startPosition = Position(1, 1, CompassPoint.S)
        marsRover = MarsRover(plateau, startPosition, instructions)

        val newPosition = marsRover.turn(Instruction.R)
        Assertions.assertEquals(CompassPoint.W, newPosition.compassPoint)
    }

    @Test
    fun whenTurningLeftFromEastThenFaceNorth() {
        val startPosition = Position(1, 1, CompassPoint.E)
        marsRover = MarsRover(plateau, startPosition, instructions)

        val newPosition = marsRover.turn(Instruction.L)
        Assertions.assertEquals(CompassPoint.N, newPosition.compassPoint)
    }

    @Test
    fun whenTurningRightFromEastThenFaceSouth() {
        val startPosition = Position(1, 1, CompassPoint.E)
        marsRover = MarsRover(plateau, startPosition, instructions)

        val newPosition = marsRover.turn(Instruction.R)
        Assertions.assertEquals(CompassPoint.S, newPosition.compassPoint)
    }

    @Test
    fun whenTurningLeftFromWestThenFaceSouth() {
        val startPosition = Position(1, 1, CompassPoint.W)
        marsRover = MarsRover(plateau, startPosition, instructions)

        val newPosition = marsRover.turn(Instruction.L)
        Assertions.assertEquals(CompassPoint.S, newPosition.compassPoint)
    }

    @Test
    fun whenTurningRightFromWestThenFaceNorth() {
        val startPosition = Position(1, 1, CompassPoint.W)
        marsRover = MarsRover(plateau, startPosition, instructions)

        val newPosition = marsRover.turn(Instruction.R)
        Assertions.assertEquals(CompassPoint.N, newPosition.compassPoint)
    }
}