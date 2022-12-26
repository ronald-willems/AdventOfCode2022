package Day23

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day23Test {

    @Test
    fun part1() {
        Day23.part1("Test")
        Day23.display()
    }

    @Test
    fun part2() {
        Day23.part2("Test")

    }

    @Test
    fun smallTest() {
        Day23.readInput("Small")
        Day23.doStep()
        Day23.doStep()
        Day23.display()
        Day23.allElves[0]
    }

    @Test
    fun input() {
        Day23.readInput("Sample")
        Day23.display()
        println(Day23.allElves.size)
        println(Day23.elvesMap.size)

    }

    @Test
    fun testMove(){
        Day23.readInput("Small")
        Day23.doStep()
        Day23.doStep()
        Day23.display()
        println(Day23.allElves[0].pos)
        println(Day23.allElves[0].nextPos())
        println()
        println(Day23.allElves[1].pos)
        println(Day23.allElves[1].nextPos())
        println()
        println(Day23.allElves[2].pos)
        println(Day23.allElves[2].nextPos())
        println()
        println(Day23.allElves[3].pos)
        println(Day23.allElves[3].nextPos())
        println()
        println(Day23.allElves[4].pos)
        println(Day23.allElves[4].nextPos())

        Day23.doStep()
        Day23.display()
        println(Day23.allElves[0].pos)

    }
}