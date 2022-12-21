package Day20


import java.io.File

object Day20 {

    val nrs = mutableListOf<IntObject>()

    fun readInput(inputType: String) {
        File("src/main/resources/Day20/${inputType}Input.txt").forEachLine { line ->
            nrs.add(IntObject(line.toLong()))
        }
    }



    fun part1(inputType: String) {
        readInput(inputType)
        val total = nrs.size
        var sorted = nrs.toMutableList()

        nrs.forEach {
            //Get new index
            var currIndex = sorted.indexOf(it)
            sorted.remove(it)

            var summed = currIndex + it.value
            var newIndex: Int = summed.mod (total-1)
            if (newIndex<0) newIndex = total + newIndex -1

            if (newIndex==0) sorted.add(it)
            else{
                sorted.add(newIndex , it)

            }
            if (inputType == "sample") println(sorted)


        }
        val zeroObject = sorted.filter { it.value ==0.toLong() }.first()
        val zeroIndex = sorted.indexOf(zeroObject)
        val nr1K = (1000+zeroIndex) % total
        println(sorted[nr1K].value)
        val nr2K = (2000+zeroIndex) % total
        println(sorted[nr2K].value)
        val nr3K = (3000+zeroIndex) % total
        println(sorted[nr3K].value)

        println(sorted[nr2K].value+sorted[nr1K].value+sorted[nr3K].value)

    }
}

class IntObject(val value: Long)