package Day10

import java.io.File

object Day10 {
    var instructions = mutableListOf<Pair<String,Int?>>()
    var cycle = 0
    var x = 1
    var totalStrength = 0
    var resultString = ""

    fun readinput() {


        File("src/main/resources/Day10/Testnput.txt").forEachLine { line ->
            val elements = line.split(" ")

            if (elements[0]=="noop")
                instructions.add(Pair(elements[0],null))
            else instructions.add(Pair(elements[0],elements[1].toInt()))
            //val elements2 = line.split( Pattern.compile("[,\\-]"))

        }
    }

    fun getSignalStrengths():Int{
        if (cycle in listOf<Int>(20,60,100, 140,180,220))  {
          println(cycle* x)
          return   cycle * x
        }

        return 0

    }

    fun part1() {
        readinput()
        instructions.forEach {
            if (it.first == "noop") {
                cycle++
                totalStrength += getSignalStrengths()
            }
            else {
                cycle++
                totalStrength += getSignalStrengths()
                cycle++
                totalStrength += getSignalStrengths()
                x+=it.second!!;

            }



        }
        println(totalStrength)

    }

    fun drawPixle (){
        var xpos=x.mod(40)
        var cyclepos = cycle.mod(40)

        if (xpos in cyclepos-2 .. cyclepos )
            resultString+="#"
        else
            resultString+="."

    }

    fun part2(){

        readinput()
        instructions.forEach {
            if (it.first == "noop") {
                cycle++
                drawPixle()
            }
            else {
                cycle++
                drawPixle()

                cycle++
                drawPixle()
                x+=it.second!!;

            }



        }

        //Print Result
        for (i in 1..6) {
            println(resultString.substring((i-1)*40..(40*i)-1))

        }



    }
}