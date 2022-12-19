package Day18

import java.io.File

object Day18 {
    val allMs = mutableListOf<Triple<Int, Int, Int>>()
    var outside = mutableListOf<Triple<Int, Int, Int>>()
    var contained = mutableListOf<Triple<Int, Int, Int>>()

    fun readInput(inputType: String) {
        File("src/main/resources/Day18/${inputType}Input.txt").forEachLine { line ->
            var elements = line.split(",")
            allMs.add(Triple(elements[0].toInt(), elements[1].toInt(), elements[2].toInt()))
        }
    }

    fun countNotConnectedSurfaces(points: List<Triple<Int, Int, Int>>): Int {
        val xSurfaces = mutableMapOf<Triple<Int, Int, Int>, Boolean>()
        val ySurfaces = mutableMapOf<Triple<Int, Int, Int>, Boolean>()
        val zSurfaces = mutableMapOf<Triple<Int, Int, Int>, Boolean>()
        points.forEach { m ->
            if (xSurfaces.get(m) == null) xSurfaces.put(m, true) else xSurfaces.remove(m)
            if (ySurfaces.get(m) == null) ySurfaces.put(m, true) else ySurfaces.remove(m)
            if (zSurfaces.get(m) == null) zSurfaces.put(m, true) else zSurfaces.remove(m)

            val mx = Triple(m.first - 1, m.second, m.third)
            if (xSurfaces.get(mx) == null) xSurfaces.put(mx, true) else xSurfaces.remove(mx)

            val my = Triple(m.first, m.second - 1, m.third)
            if (ySurfaces.get(my) == null) ySurfaces.put(my, true) else ySurfaces.remove(my)

            val mz = Triple(m.first, m.second, m.third - 1)
            if (zSurfaces.get(mz) == null) zSurfaces.put(mz, true) else zSurfaces.remove(mz)

        }
        val result = xSurfaces.size + ySurfaces.size + zSurfaces.size
        return (result)

    }


    fun part1(inputType: String): Int {
        readInput(inputType)
        val result = countNotConnectedSurfaces(allMs)
        println(result)
        return (result)
    }


    fun getPos(inp: Triple<Int, Int, Int>, x: Int, y: Int, z: Int): Triple<Int, Int, Int>? {
        val pos = Triple(inp.first + x, inp.second + y, inp.third + z)
        if (pos.first > 22) return null
        if (pos.second > 22) return null
        if (pos.third > 22) return null

        if (pos.first < 0) return null
        if (pos.second < 0) return null
        if (pos.third < 0) return null

        if (pos in allMs) return null
        if (pos in outside) return null
        return pos
    }


    fun flush(toFlush: Triple<Int, Int, Int>? = Triple(1, 1, 1)) {
        if (toFlush != null) {
            outside.add(toFlush)

            flush(getPos(toFlush, 1, 0, 0))
            flush(getPos(toFlush, -1, 0, 0))

            flush(getPos(toFlush, 0, 1, 0))
            flush(getPos(toFlush, 0, -1, 0))

            flush(getPos(toFlush, 0, 0, 1))
            flush(getPos(toFlush, 0, 0, -1))
        }
    }


    fun part2(inputType: String): Int {
        val totalSurfaces = part1(inputType)
        flush();

        var max = 6
        if (inputType == "Test") max = 21
        for (z in 1..max) {
            for (y in 1..max) {
                for (x in 1..max) {
                    var pos = Triple(x, y, z)
                    if (pos !in allMs && pos !in outside) contained.add(pos)
                }
            }
        }
        contained.forEach { println(it) }
        val result = totalSurfaces - countNotConnectedSurfaces(contained)
        println(result)
        return result
    }

    fun printAll(inputType: String) {
        readInput(inputType)
        var max = 6
        if (inputType == "Test") max = 21
        for (z in 1..max) {
            println("Level: " + z)
            println("123456789012345678901")
            for (y in 1..max) {
                for (x in 1..max) {
                    if (allMs.contains(Triple(x, y, z))) print("#") else print(".")
                }
                println()
            }
            println()
        }
    }
}