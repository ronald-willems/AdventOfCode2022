package Day19

import java.io.File
import kotlin.math.max

object Day19 {
    val blueprints = mutableListOf<Blueprint>()

    fun readInput(inputType: String) {
        File("src/main/resources/Day19/${inputType}Input.txt").forEachLine { line ->
            val elements = line.replace(":", "").split(" ")
            blueprints.add(Blueprint(elements[1].toInt(),elements[6].toInt(),elements[12].toInt(),elements[18].toInt(),elements[21].toInt(),elements[27].toInt(),elements[30].toInt()))

        }

    }

    fun part1(inputType: String):Int{
        readInput(inputType)
        var result = 0
        blueprints.forEach {

            val geodes = BluePrintMaximizer(it).maximize(24)
            result += geodes * it.id

        }

        println("End result: " + result)
        return result
    }


    fun part2(inputType: String):Int{
        readInput(inputType)
        var result = 1
        for (i in 0..2){
            val geodes = BluePrintMaximizer(blueprints[i]).maximize(32)
            result *= geodes
        }

        println("End result: " + result)
        return result

    }
}

data class Blueprint(val id:Int, val oreRobotCost:Int, val clayRobtCost:Int, val obsRobotCostOre:Int, val obsRobotCostClay:Int, val geodeRobotCostOre:Int, val geodeRobotCostObs:Int ){
     val maxOreSpendPerMinute = listOf(clayRobtCost, obsRobotCostOre, geodeRobotCostOre).maxOrNull()!!
     val maxClaySpendPerMinute = obsRobotCostClay
     val maxObsidianSpendPerMinute = geodeRobotCostObs
}


