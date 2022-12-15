package Day15

import java.io.File
import kotlin.math.absoluteValue

object Day15 {

    var sensors = mutableListOf<Sensor>()
    val xAxis = intArrayOf(0, 0)
    val yAxis = intArrayOf(0, 0)
    var yToCheck=10
    var cannotContain = mutableListOf<IntRange>()


    fun updateAxis(pos:Pair<Int,Int>){
        if (pos.first < xAxis[0]) xAxis[0] = pos.first
        if (pos.first > xAxis[1]) xAxis[1] = pos.first
        if (pos.second < yAxis[0]) yAxis[0] = pos.second
        if (pos.second > yAxis[1]) yAxis[1] = pos.second
    }

    fun getNumber( input: List<String>, pos: Int): Int {

        return input[pos].split("=")[1].toInt();
    }


    fun readInput(inputType: String) {

        File("src/main/resources/Day15/${inputType}Input.txt").forEachLine { line ->
            val elements = line.replace(",", "").replace(":", "").split(" ")
            var beacon = Beacon(getNumber(elements,8),getNumber(elements,9) )
            updateAxis(Pair(beacon.x, beacon.y))
            val sensor = Sensor(getNumber(elements,2),getNumber(elements,3) , beacon)
            updateAxis(Pair(sensor.x, sensor.y))
            sensors.add(sensor)
            //println(""+ sensor + ". Distance " + sensor.distanceToBeacon())

        }
        if (inputType=="Test") yToCheck = 2000000
    }

    fun part1(inputType: String){
        readInput(inputType)

        sensors.forEach { sensor->
            if (yToCheck in (sensor.y-sensor.distanceToBeacon())..(sensor.y+sensor.distanceToBeacon())){
                var heightToYtoCheck = (yToCheck - sensor.y).absoluteValue //hoogte
                var width = sensor.distanceToBeacon() - heightToYtoCheck
                cannotContain.add((sensor.x - width)..(sensor.x + width))


            }

        }


        var result = 0;
        val minx = cannotContain.minByOrNull { it.first }!!.first
        val maxx = cannotContain.maxByOrNull { it.last }!!.last

        for (i in minx..maxx){
            if (cannotContain.filter { it.contains(i) }.size>0) {
                if (sensors.filter { (it.beacon.x==i && it.beacon.y== yToCheck) }.size==0)
                result++
            }
        }

        println(result)

    }



    fun part2(inputType:String, max:Int){
        readInput(inputType)
        var step = 1000
        val toExamine = mutableListOf<Pair<Int,Int>>()
        sensors.forEach { it.reduction =step*2 }


        for (x in 0 ..max step step){

            for (y in 0..max step step){
                var visible = false
                for (sensor in sensors) {
                    if(sensor.withinMHDistance(x,y)) visible = true
                }
                if (!visible) toExamine.add((Pair(x,y)))
            }
        }

        println("aantal gebieden: " + toExamine.size)
      //  toExamine.forEach { println(it) }
        sensors.forEach { it.reduction =0 }

        val resultPairs = mutableListOf<Pair<Int,Int>>()
        toExamine.forEach {
            println("gebied" + toExamine.indexOf(it)+ "  "  + it)
            for (x in it.first-2*step..it.first+2*step) {

                for (y in it.second-2*step..it.second+2*step) {
                    var visible = false
                    for (sensor in sensors) {
                        if (sensor.withinMHDistance(x, y)) visible = true
                    }
                    if (!visible) resultPairs.add((Pair(x, y)))
                }
            }
        }


        println("aantal gebieden: " + resultPairs.size)
        resultPairs.forEach { println(it) }
        println(resultPairs[0].first.toLong()*4000000+resultPairs[0].second.toLong())

    }
}
