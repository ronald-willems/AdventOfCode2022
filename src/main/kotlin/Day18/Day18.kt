package Day18

import java.io.File
import kotlin.math.absoluteValue

object Day18 {
    val allMs = mutableListOf<Triple<Int,Int,Int>>()
    var xSurfaces = mutableMapOf<Triple<Int,Int,Int>,Boolean>()
    var ySurfaces = mutableMapOf<Triple<Int,Int,Int>,Boolean>()
    var zSurfaces = mutableMapOf<Triple<Int,Int,Int>,Boolean>()

    fun readInput(inputType:String){
        File("src/main/resources/Day18/${inputType}Input.txt").forEachLine { line->
            var elements= line.split(",")
            allMs.add(Triple(elements[0].toInt(),elements[1].toInt(),elements[2].toInt()))
        }


    }

    fun part1(inputType: String):Int{
        readInput(inputType)
        allMs.forEach { m->
            if (xSurfaces.get(m)==null) xSurfaces.put(m,true) else xSurfaces.remove(m)
            if (ySurfaces.get(m)==null) ySurfaces.put(m,true) else ySurfaces.remove(m)
            if (zSurfaces.get(m)==null) zSurfaces.put(m,true) else zSurfaces.remove(m)

            val mx =Triple(m.first-1, m.second,m.third)
            if (xSurfaces.get(mx)==null) xSurfaces.put(mx,true) else xSurfaces.remove(mx)

            val my =Triple(m.first, m.second-1,m.third)
            if (ySurfaces.get(my)==null) ySurfaces.put(my,true) else ySurfaces.remove(my)

            val mz =Triple(m.first, m.second,m.third-1)
            if (zSurfaces.get(mz)==null) zSurfaces.put(mz,true) else zSurfaces.remove(mz)


        }

        val result = xSurfaces.size + ySurfaces.size + zSurfaces.size
        println(result)
        return(result)

    }

    fun isConnected(a: Surface,b: Surface):Boolean{
        if (a.type == "X")
            when (b.type){
                "X" -> return (a.x==b.x && ((a.y-b.y).absoluteValue<=1 ||(a.z-b.z).absoluteValue<=1 ))
                "Y" -> return (a.z==b.z && ((a.y-b.y).absoluteValue<=1 ||(a.x-b.x).absoluteValue<=1 ))
                "Z" -> return (a.y==b.y && ((a.z-b.z).absoluteValue<=1 ||(a.x-b.x).absoluteValue<=1 ))
            }
        if (a.type == "Y")//TODO
            when (b.type){
                "X" -> return (a.x==b.x && ((a.y-b.y).absoluteValue<=1 ||(a.z-b.z).absoluteValue<=1 ))
                "Y" -> return (a.z==b.z && ((a.y-b.y).absoluteValue<=1 ||(a.x-b.x).absoluteValue<=1 ))
                "Z" -> return (a.y==b.y && ((a.z-b.z).absoluteValue<=1 ||(a.x-b.x).absoluteValue<=1 ))
            }
        if (a.type == "Z")//TODO
            when (b.type){
                "X" -> return (a.x==b.x && ((a.y-b.y).absoluteValue<=1 ||(a.z-b.z).absoluteValue<=1 ))
                "Y" -> return (a.z==b.z && ((a.y-b.y).absoluteValue<=1 ||(a.x-b.x).absoluteValue<=1 ))
                "Z" -> return (a.y==b.y && ((a.z-b.z).absoluteValue<=1 ||(a.x-b.x).absoluteValue<=1 ))
            }




        return false
    }

    fun part2(inputType: String):Int{
        var totalSurfaces = part1(inputType)
        var unLinkedSurfaces = mutableListOf<Surface>()
        xSurfaces.keys.toMutableList().forEach {
            unLinkedSurfaces.add(Surface(it.first,it.second,it.third,"X"))
        }
        ySurfaces.keys.toMutableList().forEach {
            unLinkedSurfaces.add(Surface(it.first,it.second,it.third,"Y"))
        }
        zSurfaces.keys.toMutableList().forEach {
            unLinkedSurfaces.add(Surface(it.first,it.second,it.third,"Z"))
        }


        var connectedSurfaces = mutableListOf<Surface>()
        connectedSurfaces.add(unLinkedSurfaces[0])
        unLinkedSurfaces.removeFirst()

        var canFindLinkedSurface = true
        while (canFindLinkedSurface){
            unLinkedSurfaces.forEach { ul->
                connectedSurfaces.forEach{cn ->
                    if (isConnected(ul,cn)){
                        unLinkedSurfaces.remove(ul)
                        connectedSurfaces.add(ul)

                    }
                }

            }


        }

        val result = totalSurfaces-unLinkedSurfaces.size
        println(result)
        return result

    }

    data class Surface(var x:Int, var y:Int, var z:Int, var type:String){
        override fun toString(): String = "$type:($x, $y, $z)"
    }
}