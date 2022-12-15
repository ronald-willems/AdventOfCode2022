package Day15

import kotlin.math.absoluteValue

data class Sensor(val x: Int, val y: Int, val beacon: Beacon) {

    var reduction =0


    fun distanceToBeacon():Int{
        return ((x-beacon.x).absoluteValue + (y-beacon.y).absoluteValue)- reduction

    }

    fun withinMHDistance(x1:Int, y1:Int): Boolean{
        return mhDistance(x1,y1)<=distanceToBeacon()

    }

    fun mhDistance(x1:Int, y1:Int): Int{
        return ((x-x1).absoluteValue + (y-y1).absoluteValue)

    }
}