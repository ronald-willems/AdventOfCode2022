package Day19

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day19Test {

    @Test
    fun readInput() {
        Day19.readInput("Sample")
        assertEquals(14,Day19.blueprints[0].obsRobotCostClay)
        assertEquals(3,Day19.blueprints[1].geodeRobotCostOre)
    }
}