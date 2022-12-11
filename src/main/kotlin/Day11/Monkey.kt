package Day11

class Monkey(
    val id: Int
) {
    val items = mutableListOf<Item>()

    var operator = ""
    var nrToOperate: Long? = null
    var testDivision:Long = 0
    var throwTrue = 0
    var throwFalse = 0
    var itemsInspected:Long = 0;

    fun doTurn() {
        //println("Monkey turn " + nr)
        items.forEach {
            //println("element" + it.worrylevel)
            inspectItem(it)
        }
        items.removeAll { true }


    }

    fun inspectItem(it: Item) {
        var nrO = nrToOperate;
        if (nrO == null) nrO = it.worrylevel

        it.worrylevel =
            when (operator) {

                "*" -> {
                   // if (Long.MAX_VALUE/it.worrylevel>nrO) println("Oeps!! " + id)
                    it.worrylevel * nrO
                }
                else -> it.worrylevel + nrO
            }
        //it.worrylevel = it.worrylevel.div(3)
        it.worrylevel = it.worrylevel.mod((Day11.worryDivider))

        if (it.worrylevel.mod(testDivision)==0.toLong()) {
            //println("trhow ${it.worrylevel} to ${throwTrue}")
            Day11.allMonkeys[throwTrue].catchItem(it)
        }
        else {
            //println("trhow ${it.worrylevel} to ${throwFalse}")
            Day11.allMonkeys[throwFalse].catchItem(it)
        }



        itemsInspected++

    }

    fun catchItem(item:Item){
        items.add(item)
    }


}

data class Item(var worrylevel: Long)

