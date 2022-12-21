package Day20

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day20Test {

    @Test
    fun part2() {
        Day20.part2("Test")
    }

    @Test
    fun readInput() {
        Day20.readInput("Test")
        assertEquals(Day20.nrs.size, Day20.nrs.toSet().size)
    }
}