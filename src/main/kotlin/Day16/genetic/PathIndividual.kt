package Day16.genetic

import Day16.Day16
import Day16.Path
import kotlin.random.Random

class PathIndividual(val path:Path) {
    //Genetic Algorithm
    val extraOpenValveChance = 2
    //TODO
    fun fillRandom(){
        path.addValve(Day16.firstValve)
        for (i in 1..30){
            var curr = path.valves.last()

            var nrOptions = curr.connections.size
            if (path.valves.last().rate>0 && curr !in path.turnedValves) {
                nrOptions+=extraOpenValveChance
                val option = Random.nextInt(nrOptions)
                if (option>=nrOptions-2) path.turnValve(curr)
                else path.addValve(curr.connections[option])


            } else path.addValve(curr.connections[Random.nextInt(nrOptions)])
        }

    }

    fun size():Int{
        return path.valves.size
    }





    //TODO
    fun mutate(){}
}