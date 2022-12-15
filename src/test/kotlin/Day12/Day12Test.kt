package Day12

import org.junit.jupiter.api.Test



import org.junit.jupiter.api.Assertions.*

internal class Day12Test {

    @Test
    fun testDijkstra() {

            val nodes = 6
            val edges: Array<IntArray> = Array(nodes) { IntArray(nodes) { -1 } }
            edges[0][1] = 34
            edges[0][3] = 43
            edges[1][2] = 56
            edges[1][3] = 20
            edges[1][4] = 57
            edges[2][5] = 20
            edges[3][1] = 20
            edges[3][4] = 50
            edges[4][2] = 37
            Day12.dijkstra(0, edges, nodes)
            println(edges[0].filter{ it >-1}.size)
        }


    /*@Test
    fun `should find shortest path`() {
        val graph = listOf(
            Edge2(StringNode("a"), StringNode("b"), 4),
            Edge2(StringNode("a"), StringNode("c"), 2),
            Edge2(StringNode("b"), StringNode("c"), 3),
            Edge2(StringNode("c"), StringNode("b"), 1),
            Edge2(StringNode("c"), StringNode("d"), 5),
            Edge2(StringNode("b"), StringNode("d"), 1),
            Edge2(StringNode("a"), StringNode("e"), 1),
            Edge2(StringNode("e"), StringNode("d"), 4)
        )
        val result = findShortestPath(graph, StringNode("a"), StringNode("d"))

        println(result.shortestPath() )

        assertEquals(4,result.shortestDistance() )
    }*/

    @Test
    fun part1(){
        Day12.part1("Test")
    }

}