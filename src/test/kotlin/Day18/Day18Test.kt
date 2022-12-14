package Day18

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day18Test {

    @Test
    fun readInput(){
        Day18.readInput("Sample")
    }

    @Test
    fun part1() {
        Day18.part1("Test")
    }

    @Test
    fun print() {
        Day18.printAll("Test")
    }

    @Test
    fun part2(){
        val notConn = Day18.part2("Test")
        println(notConn)
    }
}