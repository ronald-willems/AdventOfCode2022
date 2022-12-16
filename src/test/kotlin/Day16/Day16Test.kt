package Day16

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day16Test {

    @Test
    fun readInput() {
        Day16.readInput("Sample")
        println(Day16.firstValve)
        println(Day16.allValves[2])
        println(Day16.allValves[2].connections)

    }
}