package Day16

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day16Test {

    @Test
    fun readInput() {
        Day16.readInput("Sample")
        println(Day16.firstValve)
        println(Day16.sortedValves[2])
        println(Day16.sortedValves[2].connections)
        assertEquals(6,Day16.sortedValvesWithGate.size)
        assertEquals("HH",Day16.sortedValvesWithGate[0].name)

    }

    @Test
    fun part1Sample(){
        assertEquals(1651, Day16.part1("Sample"))

    }

    @Test
    fun part1(){
        assertEquals(1923, Day16.part1("Test"))

    }


    //TODO Waarom 6 te veel? Misschien winnend scenario plotten?
    @Test
    fun part2Sample(){
        assertEquals(1707, Day16.part2("Sample"))

    }

    @Test
    fun part2(){
        assertEquals(1899, Day16.part2("Test")) //1899 is te klein

    }



    @Test
    fun steps(){
        Day16.readInput("Sample")
        val pf = PathFinder()

        var next = pf.nextStep()
        println(next)
        pf.addStep(next!!)
        println(pf.state())


         next = pf.nextStep()
        println(next)
        pf.addStep(next!!)
        println(pf.state())
        println(pf.maxRemaining())


        next = pf.nextStep()
        println(next)
        pf.addStep(next!!)
        println(pf.state())
    }

    @Test
    fun trackback(){
        Day16.readInput("Sample")
        val pf = PathFinder()
        println(pf.state())
      //  println(pf.nextStep())
        println(pf.steps.last().options)
        pf.addStep(pf.nextStep()!!)
        pf.removeLast()
        pf.addStep(pf.nextStep()!!)
        pf.removeLast()

        println("moet null zijn")
        println(pf.nextStep())

/*        pf.removeLast()
        pf.addStep(pf.nextStep()!!)
        println(pf.state())
        println(pf.nextStep())
        pf.removeLast()
        pf.addStep(pf.nextStep()!!)
        println(pf.state())
        println(pf.nextStep())
        pf.removeLast()
        println(pf.nextStep())*/

    }

    @Test
    fun example(){
        //AADDDDCCBBBBAAIIJJJJ IIAADDEEFFGGHHHHGGFFEEEEDDCCCC")
        Day16.readInput("Sample")
        val pf = PathFinder()
        pf.addStep(Step(Day16.valvesMap["DD"]!!))
        pf.addStep(Step(Day16.valvesMap["DD"]!!,true))
        pf.addStep(Step(Day16.valvesMap["CC"]!!))
        pf.addStep(Step(Day16.valvesMap["BB"]!!))
        pf.addStep(Step(Day16.valvesMap["BB"]!!,true))
        pf.addStep(Step(Day16.valvesMap["AA"]!!))
        pf.addStep(Step(Day16.valvesMap["II"]!!))
        pf.addStep(Step(Day16.valvesMap["JJ"]!!))
        pf.addStep(Step(Day16.valvesMap["JJ"]!!,true))

        println(pf.nextStep())

        pf.addStep(Step(Day16.valvesMap["II"]!!))
        pf.addStep(Step(Day16.valvesMap["AA"]!!))
        pf.addStep(Step(Day16.valvesMap["DD"]!!))
        pf.addStep(Step(Day16.valvesMap["EE"]!!))
        pf.addStep(Step(Day16.valvesMap["FF"]!!))
        pf.addStep(Step(Day16.valvesMap["GG"]!!))
        pf.addStep(Step(Day16.valvesMap["HH"]!!))
        pf.addStep(Step(Day16.valvesMap["HH"]!!,true))

        pf.addStep(Step(Day16.valvesMap["GG"]!!))
        pf.addStep(Step(Day16.valvesMap["FF"]!!))
        pf.addStep(Step(Day16.valvesMap["EE"]!!))
        pf.addStep(Step(Day16.valvesMap["EE"]!!,true))
        pf.addStep(Step(Day16.valvesMap["DD"]!!))
        pf.addStep(Step(Day16.valvesMap["CC"]!!))
        pf.addStep(Step(Day16.valvesMap["CC"]!!,true))

        pf.addStep(Step(Day16.valvesMap["CC"]!!))
        pf.addStep(Step(Day16.valvesMap["CC"]!!))
        pf.addStep(Step(Day16.valvesMap["CC"]!!))
        pf.addStep(Step(Day16.valvesMap["CC"]!!))
        pf.addStep(Step(Day16.valvesMap["CC"]!!))
        pf.addStep(Step(Day16.valvesMap["CC"]!!))



        assertEquals(1651,pf.state().totalPressure)



    }




    @Test
    fun checkShortestPath(){
        Day16.readInput("Sample")
        val graph = Day16.allEdges
        assertEquals(2, findShortestPath(graph, Valve("AA",0), Valve("CC",2)).shortestDistance())
        assertEquals(3, findShortestPath(graph, Valve("FF",0), Valve("AA",0)).shortestDistance())

        assertTrue(Pair(Day16.valvesMap["FF"],3) in Day16.valvesMap["AA"]!!.destinations)

    }


}