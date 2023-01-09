package Day16

import java.util.*

class PathFinder(val maxMinute: Int = 30, val withElephant: Boolean = false) {
    val steps = mutableListOf<Step>()
    val turnedValves = mutableSetOf<Valve>()
    val statesEvaluated = mutableSetOf<State>()
    var minutesRemaining = maxMinute
    var currentPressure = 0
    var totalPressure = 0
    var maxPressureFound = 0
    val startValve = Day16.valvesMap["AA"]!!
    var currentValveMe = startValve
    var currentValveElephant: Valve? = null
    var resultSteps = listOf<Step>()


    init {
        if (withElephant) {
            steps.add(Step(startValve, false, startValve, false))
            currentValveElephant = startValve
        } else
            steps.add(Step(startValve, false))
    }

    fun state(): State {

        return State(
            minutesRemaining,
            currentValveMe,
            currentValveElephant,
            currentPressure,
            totalPressure,
            turnedValves.toMutableSet()
        )
    }

    fun maxRemaining(): Int {
        var result = currentPressure * minutesRemaining
        var countRemaining = minutesRemaining

        Day16.sortedValvesWithGate.forEach {

            if (countRemaining > 0) {
                if (it !in turnedValves) {
                    result += it.rate * (countRemaining+1)
                    countRemaining -= 2
                }
            }
        }

        return result
    }


    fun addStep(step: Step) {

        totalPressure += currentPressure
        minutesRemaining--

        steps.add(step)
        currentValveElephant = step.elephantDest
        currentValveMe = step.dest

        if (step.turn) {

            turnedValves.add(step.dest)
            currentPressure += step.dest.rate
        }

        if (step.elephantTurn) {
            turnedValves.add(step.elephantDest!!)
            currentPressure += step.elephantDest.rate
        }

    }

    fun removeLast() {
        val toRemove = steps.last()
        if (toRemove.turn) {
            currentPressure -= toRemove.dest.rate
            turnedValves.remove(toRemove.dest)

        }

        if (toRemove.elephantTurn) {
            currentPressure -= toRemove.elephantDest!!.rate
            turnedValves.remove(toRemove.elephantDest!!)
        }

        steps.removeLast()

        if (steps.size > 0) {

            currentValveMe = steps.last().dest
            currentValveElephant = steps.last().elephantDest

        } else {
            if (toRemove.elephantDest!=null )   currentValveElephant = Day16.valvesMap["AA"]!!
            else currentValveElephant = null
            currentValveMe = Day16.valvesMap["AA"]!!

        }

        totalPressure -= currentPressure
        minutesRemaining++

    }


    override fun toString(): String {
        return steps.map { if (it.turn) it.dest.name.lowercase(Locale.getDefault()) else it.dest.name }.toString()
    }


    fun nextStep(): Step? {


        val lastStep = steps.last()
        if (lastStep.options == null) {

            val currState = state()
            if (currState in statesEvaluated) return null else statesEvaluated.add(currState)

            if (totalPressure + maxRemaining() < maxPressureFound) return null


            lastStep.options = mutableListOf<Step>()
            val options = lastStep.options!!

            val currEl = currentValveElephant
            if (currEl==null){

                if (currentValveMe !in turnedValves && currentValveMe.rate > 0) options.add(Step(currentValveMe, true))

                currentValveMe.connections.forEach {
                    options.add(Step(it, false))

                }
            }
            else{
                //Part 2
                val optionsMe = currentValveMe.connections.toMutableList()
                if (currentValveMe !in turnedValves && currentValveMe.rate > 0) optionsMe.add(currentValveMe)

                val optionsElephant = currEl.connections.toMutableList()
                if (currEl !in turnedValves && currEl.rate > 0 && currentValveMe != currEl) optionsElephant.add(currEl)

                optionsMe.forEach { connMe ->
                    optionsElephant.forEach { connEl ->
                        var turnMe = false
                        var turnEl = false
                        if (connMe == currentValveMe) turnMe = true
                        if (connEl == currentValveElephant) turnEl = true
                        options.add(Step(connMe, turnMe, connEl, turnEl))

                    }

                }

            }


        }

        if (lastStep.options!!.size != 0) {
            val toReturn = lastStep.options!!.first()
            lastStep.options!!.removeFirst()
            return toReturn
        } else return null
    }



    fun maximize(): Int {

        var possible = true

        var it = 0
        while (possible) {
            /*       if (it>500) {
                       println(state())
                       return maxPressureFound
                   }*/
            it++

            val nextStep = nextStep()

            if (nextStep != null) {
                addStep(nextStep)


            } else {
                if (steps.size == 1) {
                    possible = false

                }
                removeLast()

                continue
            }

            if (minutesRemaining == 0) {
                if (totalPressure > maxPressureFound) {
                    maxPressureFound = totalPressure
                    println("Max gevonden: " + maxPressureFound + " Iteratie: " + it)
                    resultSteps = steps.toList()
                   // if (maxPressureFound>1707) Day16.printResult(resultSteps.toMutableList())
                }
                removeLast()
            }
        }
        return maxPressureFound
    }
}