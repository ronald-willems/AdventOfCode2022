package Day12.self


import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach

internal class Day12Test {

    @BeforeEach
    internal fun setUp() {
        Day12Self.readinput("Sample")
    }

    @Test
    fun readinput() {

        assertEquals(1,Day12Self.map[Pair(0,0)])
        assertEquals(2, Day12Self.map[Pair(2,0)])
        assertEquals(26,Day12Self.map[Pair(5,2)])
        assertEquals( Pair(5,2), Day12Self.end )




    }

    @Test
    fun testPossibleMoves() {

        val newPath = Path(mutableListOf(Pair(1,0),Pair(2,0)))
        Day12Self.pointsVisited.put(Pair(1,0),1)
        Day12Self.pointsVisited.put(Pair(2,0),2)

        assertEquals(1,newPath.possibleMoves().size)
        val newPath2 = Path(mutableListOf(Pair(1,0),Pair(2,0),Pair(2,1)))
        Day12Self.pointsVisited.put(Pair(2,1),3)
        assertEquals(2,newPath2.possibleMoves().size)

        val newPath3 = Path(mutableListOf(Pair(4,1)))
        Day12Self.pointsVisited.clear()
        Day12Self.pointsVisited.put(Pair(4,1),1)
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