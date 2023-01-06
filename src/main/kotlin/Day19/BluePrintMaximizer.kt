package Day19

class BluePrintMaximizer(val blueprint: Blueprint) {

    fun maximize(maxMinute:Int): Int {
        val scenario = Scenario(blueprint,maxMinute) //already creates the first minute
        var possible = true
        var maxGeodes = 0
        var it = 0
        while (possible) {
            it++

            val nextStep = scenario.nextStep()

            if (nextStep != null ) {
                scenario.addStep(nextStep)
           //     tried.add(Pair(currSit,nextStep))

            } else {
                if (scenario.steps.size == 1) possible = false
                scenario.removeLast()

                continue
            }

            if (scenario.steps.size == maxMinute) {

                if (scenario.geodes > maxGeodes) {
                    maxGeodes = scenario.geodes
                    println("Max gevonden: " + maxGeodes + " Iteratie: " + it + " Blueprint: " + blueprint.id)
                }

                if (scenario.steps.size == 1) possible = false
                scenario.removeLast()

            }

        }
        return maxGeodes
    }
}