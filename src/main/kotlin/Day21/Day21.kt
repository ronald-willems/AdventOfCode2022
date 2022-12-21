package Day21

import java.io.File

object Day21 {

    val allMonkeys = mutableMapOf<String,Monkey>()
    val sortedMonkeys = mutableListOf<Monkey>()

    fun readInput(inputType: String) {
        File("src/main/resources/Day21/${inputType}Input.txt").forEachLine { line ->
            val elements = line.replace(":", "").split(" ")
            if (elements[1][0].isDigit()){
                allMonkeys.put(elements[0],NrMonkey(elements[0],elements[1].toInt()))
            }
            else {
                allMonkeys.put(elements[0], MathMonkey(elements[0], elements[1], elements[2], elements[3]))
            }
        }
    }

    fun part1(inputType: String):Long{
        readInput(inputType)
        val monkeysToSort = mutableListOf<Monkey>()
        monkeysToSort.add( allMonkeys.get("root")!!)

        while (monkeysToSort.size>0){
              val toHandle = monkeysToSort.toMutableList()
            toHandle.forEach {
                sortedMonkeys.add(it)
                monkeysToSort.remove(it)
                if (it is MathMonkey){
                    monkeysToSort.add(allMonkeys.get(it.dep1)!!)
                    monkeysToSort.add(allMonkeys.get(it.dep2)!!)
                }
            }
        }

        var rootYell = 0.toLong();
        sortedMonkeys.reversed().forEach {
            var yell = it.yell()!!
            if (it.name=="root") rootYell = yell

        }

        println("Root! " + rootYell)
        return(rootYell)

    }

    fun part2(inputType: String):Long{
        part1(inputType)

        //Manually checked which monkey the root depends on was influenced by humn
        var toMatch = allMonkeys[(allMonkeys["root"]!! as MathMonkey).dep2]!!.yell()!!
        var currMonkey = allMonkeys[(allMonkeys["root"]!! as MathMonkey).dep1]!!

        while (currMonkey.name != "humn"){
            //currMonkey has to yell 'to Match'
            println("Who: " + currMonkey.name + "To Match: " + toMatch + "Class: " +  currMonkey.javaClass)
            println(toMatch)
            toMatch = currMonkey.toMatchPredecessor(toMatch)
            currMonkey = currMonkey.humanInfluencer()!!

        }

        println("To match for humn: " + toMatch)

        return toMatch
    }
}