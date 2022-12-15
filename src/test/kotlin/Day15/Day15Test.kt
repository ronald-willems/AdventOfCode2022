package Day15

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day15Test {

    @Test
    fun part1() {
        Day15.part1("Test")
    }

    @Test
    fun calc(){
        var x :Long = 2870615
        var y: Long = 2818989
        println(x*4000000+y)

    }

    @Test
    fun part2() {
        Day15.part2("Test",4000000)
        //4000000
        //(2870615, 2818989)
    }
}