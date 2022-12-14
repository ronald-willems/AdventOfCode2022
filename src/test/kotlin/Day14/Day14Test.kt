package Day14

import Day14.Day14.yAxis
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day14Test {

    @Test
    fun readInput() {
        Day14.readInput("Sample")
        Day14.displayGrid()
        println(yAxis[0])
        println(yAxis[1])
    }

    @Test
    fun part1(){
        Day14.part1("Test")
    }

    @Test
    fun part2(){
        Day14.part2("Test")
    }
}