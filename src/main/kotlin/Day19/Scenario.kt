package Day19

class Scenario (val blueprint: Blueprint,val maxMinute:Int) {
    val steps = mutableListOf<Step>()
    val minutesRemaining get() = maxMinute - steps.size
    val statesEvaluated = mutableSetOf<State>()


    //TODO: Refactor to state object.
    var orebots =1;
    var claybots =0
    var obsedianbots =0
    var geodebots =0
    var ore =0
    var clay =0
    var obsidian =0
    var geodes =0


    init {
        val baseStep = Step(0,0,0,0,blueprint)
        addStep(baseStep)

    }

    fun state():State{
        //TODO Refactor.
        return State(steps.size, orebots,claybots,obsedianbots,geodebots, ore,clay,obsidian,geodes)
    }

    fun couldUseMoreObsidian() = obsidian + obsedianbots * minutesRemaining < (minutesRemaining * blueprint.maxObsidianSpendPerMinute)
    fun couldUseMoreClay() = clay + claybots * minutesRemaining < (minutesRemaining * blueprint.maxClaySpendPerMinute)
    fun couldUseMoreOre() = ore + orebots * minutesRemaining < (minutesRemaining * blueprint.maxOreSpendPerMinute)


    fun canBuildOreBot():Boolean {
        return couldUseMoreOre() && ore>=blueprint.oreRobotCost
    }

    fun canBuildClayBot():Boolean {
        return couldUseMoreClay() && ore>=blueprint.clayRobtCost
    }

    fun canBuildNewObsBot():Boolean{
        return couldUseMoreObsidian() && ore>=blueprint.obsRobotCostOre && clay>=blueprint.obsRobotCostClay
    }

    fun canBuildNewGeodeBot():Boolean{
        return obsidian>=blueprint.geodeRobotCostObs && ore>=blueprint.geodeRobotCostOre
    }

    fun canBuyEverything():Boolean{
        return blueprint.oreRobotCost<=ore && blueprint.clayRobtCost<=ore &&
                blueprint.obsRobotCostOre<=ore && blueprint.obsRobotCostClay<=clay &&
                blueprint.geodeRobotCostOre<=ore && blueprint.geodeRobotCostObs<=obsidian
    }

    fun harvest(step:Step){
        step.ore += orebots
        step.clay += claybots
        step.obsidian += obsedianbots
        step.geodes += geodebots
    }

    fun addStep(step:Step){
        harvest(step)
        steps.add(step)

         orebots += step.orebots
         claybots += step.claybots
         obsedianbots += step.obsedianbots
         geodebots += step.geodebots
         ore += step.ore
         clay += step.clay
         obsidian += step.obsidian
         geodes += step.geodes

    }

    fun removeLast(){
        val step = steps.last()

        orebots -= step.orebots
        claybots -= step.claybots
        obsedianbots -= step.obsedianbots
        geodebots -= step.geodebots
        ore -= step.ore
        clay -= step.clay
        obsidian -= step.obsidian
        geodes -= step.geodes

        steps.removeLast()

    }


    fun nextStep():Step?{

        val lastStep = steps.last()
         if (lastStep.options==null) {

             val currState = state()
             if (currState in statesEvaluated) return null else statesEvaluated.add(currState)

             lastStep.options = mutableListOf<Step>()
             val options = lastStep.options!!

             if (canBuildNewGeodeBot()) {
                 options.add(Step(0, 0, 0, 1, blueprint))

             }
             else {
                 if (canBuildNewObsBot())
                     options.add(Step(0, 0, 1, 0, blueprint))

                 if (canBuildClayBot())
                     options.add(Step(0, 1, 0, 0, blueprint))

                 if (canBuildOreBot())
                     options.add(Step(1, 0, 0, 0, blueprint))

                 if (!canBuyEverything())
                     options.add( Step(0, 0, 0, 0, blueprint))
             }
         }

        if (lastStep.options!!.size!=0) {
            val toReturn = lastStep.options!!.first()
            lastStep.options!!.removeFirst()
            return toReturn
        }
        else return null

    }

    override fun toString():String{
        return "Orebots: $orebots. Claybots: $claybots. ObsedianBots: $obsedianbots. GeodeBots: $geodebots. Ore: $ore. Clay: $clay. Obsedian: $obsidian. Geodes: $geodes."


    }

}