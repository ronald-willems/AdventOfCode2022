package Day13


import java.io.File

object Day13 {
    private val myComparator =  Comparator<List<Any>> { a, b ->
        when {
            rightOrder(a,b)==true -> -1

            else -> 1

        }
    /*    when (rightOrder(a,b) {
            null -> 1

            (a == null && b == null) -> 0
            (a == null) -> -1
            else -> 1
        }*/
    }


    var lines = mutableListOf<String>()
    //var allPairs = mutableListOf<Pair<MutableList<Any>,MutableList<Any>>>()
    var sumIndices = 0
    var index = 1

    fun findListEnd(input:String):Int{
        var index = 0;
        var stack = 0
        input.forEach {
            if (it.toString()=="[") stack++
            if (it.toString()=="]") stack--
            if (stack==0) return index;
            index++

        }
        return 0;

    }

    fun parseList( input: String):MutableList<Any>{
        var result = mutableListOf<Any>()
        var toHandle = input.substring(1,input.length)
      //  println(toHandle)

        while(toHandle.length>1) {
            if (toHandle[0].toString() == "[") {

                val endIndex = findListEnd(toHandle.substring(0, toHandle.length - 1)) +1
                result.add(parseList(toHandle.substring(0, endIndex)))
                toHandle = toHandle.substring(endIndex+1,toHandle.length)
                //println("toHandle" + toHandle)

            } else {
                val nr = toHandle.substringBefore(",").substringBefore("]").toInt()
             //   println(nr)
                result.add(nr)
                if (!toHandle.contains(",")) toHandle = ""
                toHandle = toHandle.substringAfter(",")
           //     println(toHandle)
            }
        }
        return result
    }







    fun rightOrder(left:List<Any>,right:List<Any>):Boolean?{
       // println("left: " + left + "right  " + right)


        for (i in 0.. left.size-1){
            if(i>right.size-1) return false
            //println("left part $i:" + left[i] + "right part " + right[i])
            var rightOrder:Boolean? = null

            if (left[i] is List<*> && right[i] is List<*>) rightOrder = rightOrder(left[i] as List<Any>,right[i] as List<Any>)
            if (left[i] is Int && right[i] is List<*>) rightOrder = rightOrder(mutableListOf(left[i] as Int),right[i] as List<Any>)
            if (left[i] is List<*> && right[i] is Int) rightOrder=  rightOrder(left[i] as List<Any>,mutableListOf(right[i] as Int))

            if (left[i] is Int && right[i] is Int) {
               // println("Integers: " + left[i]+ " " + right[i])
                if ((left[i] as Int) < (right[i] as Int)) rightOrder = true;
                if ((left[i] as Int) > (right[i] as Int)) rightOrder = false;

            }

            if (rightOrder!=null) return rightOrder
        }
        if (right.size > left.size) return true

        return null
    }


    fun part1(inputType:String){
        File("src/main/resources/Day13/${inputType}Input.txt").forEachLine { line ->
            lines.add(line)
        }

        for (i in 0..lines.size-3 step 3){
            val left = parseList(lines[i])
            val right = parseList(lines[i+1])
            val rightOrder = rightOrder(left,right)
            if (rightOrder!= null && rightOrder==true){
                println("Yes: " + index)
                sumIndices+=index
            }

            index++

        }

        println(sumIndices)

    }

    fun part2(inputType: String){
        var toProcess = mutableListOf<List<Any>>()


            File("src/main/resources/Day13/${inputType}Input.txt").forEachLine { line ->
                if (line!="")
                    toProcess.add(parseList(line))
                //result.sortedWith()
            }
        toProcess.add(parseList("[[2]]"))
        toProcess.add(parseList("[[6]]"))
        val result = toProcess.sortedWith(myComparator)
        println(result[0])

        var decoderKey = 1
        for (i in 1..result.size){
            if (result[i-1].toString()=="[[2]]") decoderKey*=i
            if (result[i-1].toString()=="[[6]]") decoderKey*=i

        }
        println(decoderKey)




    }


}