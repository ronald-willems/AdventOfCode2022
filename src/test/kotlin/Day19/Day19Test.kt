package Day19

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.system.measureTimeMillis

internal class Day19Test {

    @Test
    fun readInput() {
        Day19.readInput("Sample")
        assertEquals(14,Day19.blueprints[0].obsRobotCostClay)
        assertEquals(3,Day19.blueprints[1].geodeRobotCostOre)
    }

    @Test
    fun testPart1Sample(){
        assertEquals (33,Day19.part1("Sample"))
    }

    @Test
    fun testPart1(){
        println(Day19.part1("Test"))
    }

    @Test
    fun testPart2(){
        val elapsed = measureTimeMillis {
            println(Day19.part2("Test"))
        }
        println("Time: " + (elapsed/1000))


    }


    @Test
    fun testMaximizer(){
        Day19.readInput("sample")
        println(BluePrintMaximizer(Day19.blueprints[0]).maximize(24))

    }

    @Test
    fun testMaximizer2(){
       Day19.readInput("sample")
        val bp = Day19.blueprints[0]
        val scenario = Scenario(bp,24)
        println(scenario)
        println(scenario.orebots)
        println(scenario.ore)

        var toAdd = Step(0,0,0,0,bp)

        scenario.addStep(toAdd)
        println(scenario)

         toAdd = Step(0,1,0,0,bp)

        scenario.addStep(toAdd)

        toAdd = Step(0,0,0,0,bp)

        scenario.addStep(toAdd)

        toAdd = Step(0,1,0,0,bp)

        scenario.addStep(toAdd)
        println(scenario)



        }



}