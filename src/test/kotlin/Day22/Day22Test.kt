package Day22

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day22Test {

    @Test
    fun part1() {
        assertEquals(75254,Day22.findPath("Test"))
        Day22.display()
        //91472 to high
        //63300 to low
        //75252 to low
    }

    @Test
    fun part2() {
        Day22.initCube()
        assertEquals(5031,Day22.findPath("Sample"))
        Day22.display()
        //91472 to high
        //63300 to low
        //75252 to low
    }


    @Test
    fun readInput() {
        Day22.readInput("Test")
        println(Day22.map[0] + "!")
        println(Day22.map[1] + "!")
        println(Day22.map[110] + "!")
        println(Day22.map[200] + "!")
        println(Day22.map.size)
        println(Day22.map[1].length)



    }

    @Test
    fun part1Test() {
        Day22.readInput("Sample")


        Day22.pos.x = Day22.map[1].indexOf(".") //Go to start
        assertEquals(Position(9,1),(Day22.pos))
        Day22.doInstruction("10")
        assertEquals(Position(11,1),(Day22.pos))
        Day22.doInstruction("R")
        assertEquals(Direction("D"),Day22.dir)
        Day22.doInstruction("5")
        assertEquals(Position(11,6),(Day22.pos))
        Day22.doInstruction("L")
        assertEquals(Direction("R"),Day22.dir)
        Day22.doInstruction("5")
        assertEquals(Position(4,6),(Day22.pos))




    }
}