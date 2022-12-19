package Day19

import java.io.File

object Day19 {
    val blueprints = mutableListOf<Blueprint>()

    fun readInput(inputType: String) {
        File("src/main/resources/Day19/${inputType}Input.txt").forEachLine { line ->
            val elements = line.replace(":", "").split(" ")
            blueprints.add(Blueprint(elements[1].toInt(),elements[6].toInt(),elements[12].toInt(),elements[18].toInt(),elements[21].toInt(),elements[27].toInt(),elements[30].toInt()))



        }

    }
}

data class Blueprint(val id:Int, val oreRobotCost:Int, val clayRobtCost:Int, val obsRobotCostOre:Int, val obsRobotCostClay:Int, val geodeRobotCostOre:Int, val geodeRobotCostObs:Int )


