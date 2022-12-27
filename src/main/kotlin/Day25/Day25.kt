package Day25

import java.io.File

object Day25 {
    val allSNAFUs = mutableListOf<String>()

    val sNAFUBaseNRs = mutableMapOf<Int, Long>()
    val snafuTops = mutableListOf<Long>()


    fun readInput(inputType: String) {
        var y = 0
        File("src/main/resources/Day25/${inputType}Input.txt").forEachLine { line ->
            allSNAFUs.add(line)
        }

        var snafTop = ""
        for (i in 1..22) {
            snafTop += "2"

            val dec = convertSNAFU(snafTop)
            snafuTops.add(dec)

        }
    }

    fun getPreviousSnafuTop(nr: Int): Long {
        if (nr == 0) return 0.toLong()
        else return snafuTops[nr - 1]
    }

    fun snafuLength(nr: Long): Int {
        var snafuLength = 0
        for (i in 0..21) {
            if (nr <= snafuTops[i]) {
                snafuLength = i + 1
                break
            }

        }
        return snafuLength
    }

    fun toSNAFUDigit(digit: Int): String {
        return when (digit) {
            -2 -> "="
            -1 -> "-"
            else -> digit.toString()

        }
    }

    fun toSnafu(nr: Long): String {

        val snafuLength = snafuLength(nr)
        var result = ""
        var current: Long = 0
        var rest = nr
        for (i in snafuLength - 1 downTo 0) {
            val base = getSNAFUBase(i)
            val ptop = getPreviousSnafuTop(i)
            /*  println("REST")
              println(rest)
              println(ptop)
              println(base)*/

            var baseTimes = 0

            baseTimes =
                if (rest > base + ptop) 2
                else if (rest >= base - ptop) 1
                else if (-1 * base + ptop < rest && rest < base - ptop) 0
                else if (rest < -base - ptop) -2
                else -1

            result += toSNAFUDigit(baseTimes)
            rest = rest - baseTimes * base


        }

        return result

    }

    fun getSNAFUBase(basNR: Int): Long {
        if (sNAFUBaseNRs.get(basNR) != null) return sNAFUBaseNRs.get(basNR)!!


        //val result = Math.pow(5.toDouble(),basNR.toDouble()).toLong()
        var result: Long = 1
        for (i in 1..basNR) {
            result *= 5
        }
        sNAFUBaseNRs.put(basNR, result)

        return result

    }

    fun convertSNAFU(snafu: String): Long {
        var reversed = snafu.reversed()
        var result: Long = 0
        for (i in 0..reversed.length - 1) {

            var nr = 0
            if (reversed[i].toString() == "=") nr = -2
            else
                if (reversed[i].toString() == "-") nr = -1
                else nr = reversed[i].toString().toInt()



            result += (nr * getSNAFUBase(i))


        }


        return result
    }

    fun part1(inputType: String): Long {
        readInput(inputType)
        var result: Long = 0
        allSNAFUs.forEach {
            result += convertSNAFU(it)
        }

        println(result)
        return result
    }
}