package Day11


import java.io.File

object Day11 {

    val allMonkeys = mutableListOf<Monkey>()
    var worryDivider: Long = 1

    fun readinput() {

        var currentMonkey = Monkey(0)

        File("src/main/resources/Day11/TestInput.txt").forEachLine { line ->
            if (line != "") {
                val elements = line.trim().split(" ")
                when (elements[0]) {

                    "Monkey" -> {
                        currentMonkey = Monkey(elements[1].substring(0,1).toInt())
                        allMonkeys.add(currentMonkey)
                    }
                    "Starting" -> {

                        for (i in 1..elements.size - 2) {
                            var item = Item(elements[i + 1].substringBeforeLast(",").toLong())
                            currentMonkey.items.add(item)
                        }
                    }
                    "Operation:" -> {
                        currentMonkey.operator = elements[4]
                        if (elements[5]=="old")
                        currentMonkey.nrToOperate = null
                        else currentMonkey.nrToOperate = elements[5].toLong()
                    }
                    "Test:" -> {
                        currentMonkey.testDivision = elements[3].toLong();
                        worryDivider *=currentMonkey.testDivision
                    }
                    "If" -> {
                        if (elements[1] == "true:")
                            currentMonkey.throwTrue = elements[5].toInt()
                        else currentMonkey.throwFalse = elements[5].toInt()


                    }


                }
            }





            //val elements2 = line.split( Pattern.compile("[,\\-]"))

        }
    }

    fun printMonkeyBusiness(){

        allMonkeys.sortByDescending { it.itemsInspected }
        var monkeyBusiness = allMonkeys[0].itemsInspected* allMonkeys[1].itemsInspected
        println(monkeyBusiness)
    }


    fun part1() {
        readinput()


        for (round in 1 ..20 ){
            println("Round" + round)
            allMonkeys.forEach {

                it.doTurn()
            }
            allMonkeys.forEach {
                println(it.items)
            }


        }




        printMonkeyBusiness()


    }

    fun part2(){
        readinput()
        println("end input")
        println(worryDivider)
        for (round in 1 ..10000 ) {

            allMonkeys.forEach {

                it.doTurn()
            }

            if (round in listOf(1,20,1000)){
                for (i in 0..3)
                println("Monkey ${allMonkeys[i].id}: ${allMonkeys[i].itemsInspected}")
            }
        }

        printMonkeyBusiness()

    }
}

