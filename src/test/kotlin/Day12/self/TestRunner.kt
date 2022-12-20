package Day12.self

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TestRunner {

    @Test
    fun readFullInput() {
        Day12Self.readinput("Test")
        println(Day12Self.map.get(Pair(126,34)))


    }

    @Test
    fun part1Sample() {
        assertEquals(31,Day12Self.part1("Sample"))
    }

    @Test
    fun part1Test() {
        assertEquals(423,Day12Self.part1("Test"))
    }

    @Test
    fun part2() {
        Day12Self.part2("Test")

    }
}