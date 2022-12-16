package Day16

import org.junit.jupiter.api.Test

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
}