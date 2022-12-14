package Day12

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class Day12Test {

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
        assertEquals(1, Day12.openPaths.size)



    }

    @Test
    fun testPossibleMoves() {
        assertEquals(2,Day12.openPaths[0].possibleMoves().size)
        val newPath = Path(mutableListOf(Pair(1,0),Pair(2,0)))
        assertEquals(1,newPath.possibleMoves().size)
        val newPath2 = Path(mutableListOf(Pair(1,0),Pair(2,0),Pair(2,1)))
        assertEquals(2,newPath2.possibleMoves().size)
        val newPath3 = Path(mutableListOf(Pair(4,1)))
        assertEquals(4,newPath3.possibleMoves().size)

    }

    @Test
    fun testDistance() {
        val newPath = Path(mutableListOf(Pair(1,0),Pair(2,0)))
        assertEquals(5,newPath.distance)
        val newPath2 = Path(mutableListOf(Pair(1,0),Pair(2,0),Pair(2,1)))
        assertEquals(4,newPath2.distance)

    }


}