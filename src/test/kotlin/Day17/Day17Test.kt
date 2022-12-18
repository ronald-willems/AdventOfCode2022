package Day17

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day17Test {

    @Test
    fun readInput() {
        Day17.readInput("Test")
        Day17.printBottom()
        println(Day17.instructions.length)

    }



    @Test
    fun part1() {
        assertEquals(3090,Day17.part1("Test"))
        Day17.printBottom()

    }

    @Test
    fun part2() {
        println(Day17.part2("Test"))
        Day17.printBottom()

    }

    @Test
    fun calcPart2(){
        //Een keer input is 2647 hoogte.
        //Een keer input is 1730 rocks
        println(1000000000000.div(1730)) //578034682
        println(1000000000000.mod(1730)) //140

        //578034682 * 2647 + hoogte van 140 rocks
        //578034681 * 2647 + hoogte van 140 + 1730

    }

    @Test
    fun part2b() {
        var result = Day17.part2b("Test",(1730+140).toLong())
        Day17.printBottom()
        println(result)
        var extra:Long = 578034681.toLong()*2647
        println(extra + result)
        println()

    }
}