package Day19

data class Step (

    val orebots :Int,
    val claybots :Int,
    val obsedianbots : Int,
    val geodebots : Int,
    val blueprint:Blueprint)
{
    var ore = 0
    var clay = 0
    var obsidian = 0
    var geodes = 0


    var options : MutableList<Step>? = null

    init{
        //Calculate cost
        ore = -blueprint.oreRobotCost * orebots - blueprint.clayRobtCost*claybots - blueprint.obsRobotCostOre*obsedianbots - blueprint.geodeRobotCostOre*geodebots
        clay = -blueprint.obsRobotCostClay*obsedianbots
        obsidian = -blueprint.geodeRobotCostObs*geodebots


    }
}
