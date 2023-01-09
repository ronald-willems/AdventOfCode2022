package Day16

import java.util.*

class PathFinder(val maxMinute: Int = 30) {
    val steps = mutableListOf<Step>()
    val turnedValves = mutableSetOf<Valve>()
    val statesEvaluated = mutableSetOf<State>()
    var minutesRemaining = maxMinute
    var currentPressure = 0
    var totalPressure = 0
    var maxPressureFound = 0
    var currentValveMe = Day16.valvesMap["AA"]!!
    var currentValveElephant = Day16.valvesMap["AA"]!!
    var resultSteps = listOf<Step>()


    init {
        steps.add(Step(Day16.valvesMap["AA"]!!, false, true))
    }

    fun state(): State {
        return State(minutesRemaining, currentValveMe, currentValveElephant, currentPressure, totalPressure,  turnedValves.toMutableSet())
    }

    fun maxRemaining(): Int {
        var result = currentPressure * minutesRemaining
        var countRemaining = minutesRemaining

        Day16.sortedValvesWithGate.forEach {

            if (countRemaining > 0) {
                if (it !in turnedValves) {
                    result += it.rate * (countRemaining)
                    countRemaining -= 2
                }
            }
        }

        return result
    }


    fun addStep(step: Step) {
        if (!step.elephant) {
            totalPressure += currentPressure
            minutesRemaining--
        }
        steps.add(step)
        if (step.elephant) currentValveElephant = step.dest else
            currentValveMe = step.dest

        if (step.turn) {

            turnedValves.add(step.dest)
            currentPressure += step.dest.rate
        }

    }

    fun removeLast() {
        val toRemove = steps.last()
        if (toRemove.turn) {
            currentPressure -= toRemove.dest.rate
            turnedValves.remove(toRemove.dest)


        }

        steps.removeLast()
        if (steps.size > 2) {
            if (toRemove.elephant) currentValveElephant = steps[steps.size - 2].dest
            currentValveMe = steps[steps.size - 2].dest

        } else {
            if (toRemove.elephant) currentValveElephant = Day16.valvesMap["AA"]!!
            currentValveMe = Day16.valvesMap["AA"]!!

        }

        if (!toRemove.elephant) {
            totalPressure -= currentPressure
            minutesRemaining++
        }
    }


    override fun toString(): String {
        return steps.map { if (it.turn) it.dest.name.lowercase(Locale.getDefault()) else it.dest.name }.toString()
    }


    fun nextStep(): Step? {
        var from = currentValveMe
        var elephant = false
        if (Day16.part2) {
            if (!steps.last().elephant) {
                from = currentValveElephant
                elephant = true
            }
        }


        val lastStep = steps.last()
        if (lastStep.options == null) {

            val currState = state()
            if (currState in statesEvaluated) return null else statesEvaluated.add(currState)

            if (totalPressure + maxRemaining() < maxPressureFound) return null


            lastStep.options = mutableListOf<Step>()
            val options = lastStep.options!!

            if (from !in turnedValves && from.rate > 0) options.add(Step(from, true, elephant))

            from.connections.forEach {
                options.add(Step(it, false, elephant))

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
                }
                removeLast()
            }
        }
        return maxPressureFound
    }
}