package Day3

import java.io.File

object Day3 {

    fun telPunten(karakter: Char): Int {
        //var nrA = "a".toCharArray()[0].code
        if (karakter.code >= 97) {
            //dit is een kleine letter
            return karakter.code - 96
        } else {
            return karakter.code - 64 + 26
            //dit is een grote letter//A = 65
        }
    }

    fun part1() {
        var totaalPunten = 0;
        File("src/main/resources/Day3/TestInput.txt").forEachLine { line ->
            var deel1 = line.substring(0, line.length / 2)
            var deel2 = line.substring(line.length / 2, line.length)
            var gevondenFouten = ""
            deel1.forEach {
                if (it in deel2 && it !in gevondenFouten) {
                    var punten = telPunten(it)
                    totaalPunten = totaalPunten + telPunten(it)
                    gevondenFouten = gevondenFouten + it
                }
            }
        }
        println(totaalPunten)
    }

    fun part2() {
        var totaalPunten = 0;
        var lineNr = 1
        var group = mutableListOf<String>()
        File("src/main/resources/Day3/TestInput.txt").forEachLine { line ->
            group.add(line)
            if (lineNr.mod(3) == 0) {
                var gevonden = false
                group[0].forEach {
                    if (it in group[1] && it in group[2] && !gevonden) {
                        totaalPunten = totaalPunten + telPunten(it)
                        gevonden = true
                    }


                }

                group.removeAll { true };
            }
            lineNr ++


        }
        println(totaalPunten)


    }


}
