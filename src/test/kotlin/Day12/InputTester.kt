package Day12

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class InputTester {

    @BeforeEach
    internal fun setUp() {
        Day12.readinput("Sample")
    }

    @Test
    fun readinput() {

        assertEquals(1,Day12.map[Pair(0,0)])
        assertEquals(2, Day12.map[Pair(2,0)])
        assertEquals(26,Day12.map[Pair(5,2)])
        assertEquals( Pair(5,2), Day12.end )

    }

    @Test
    fun posToIntToPos(){

        var pair = Pair(5,4)
        assertEquals(37,(Day12.posToInt(pair)))
        assertEquals(5,Day12.intToPos(Day12.posToInt(pair)).first)

    }








}