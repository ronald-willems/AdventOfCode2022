package Day16

import Day16.genetic.PathIndividual
import org.junit.jupiter.api.Test
import kotlin.random.Random

import org.junit.jupiter.api.Assertions.*


internal class PathTest {

    @Test
    fun calcPressure() {
        Day16.readInput("Sample")
        var path = Path()
        path.initValves("AADDDDCCBBBBAAIIJJJJIIAADDEEFFGGHHHHGGFFEEEEDDCCCC")
       // println(path.valves.size)
        assertEquals(1651,path.calcPressure())
    }

    @Test
    fun testbla(){
        for (i in 1..20)
        println(Random.nextInt(3))

    }

    @Test
    fun testRandom(){
        Day16.readInput("Sample")
        var pi = PathIndividual(Path());
        pi.fillRandom()
        println(pi.path)
        println(pi.path.valves.size)
        println(pi.path.valves.toSet().size)
        println(pi.path.calcPressure())
    }
}