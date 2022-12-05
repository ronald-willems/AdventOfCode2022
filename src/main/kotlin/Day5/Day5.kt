package Day5

import java.io.File

object Day5 {

    var instructions = mutableListOf<Triple<Int, Int, Int>>()
    var stacks = mutableListOf<MutableList<Char>>()

    fun readStack(lines: List<String>) {
        for (i in 1..9 ){
            stacks.add(mutableListOf())
        }

        var reversed = lines.asReversed()

        for (line in reversed) {
            if (line[1].toString()!="1"){
                for (i in 0..8) {
                    var pos = 4 * (i+1) - 3
                    var char = line[pos]
                    if (char.toString()!=" ") stacks[i].add(char);
                }
            }
        }
    }

    fun readinput() {
        var endOfStacks = false
        var stackLines = mutableListOf<String>();
        File("src/main/resources/Day5/TestInput.txt").forEachLine { line ->
            if (!endOfStacks) {
                if (line == "") {
                    endOfStacks = true;
                    readStack(stackLines)
                } else {
                    stackLines.add(line)

                }
            } else {
                val elements = line.split(" ")
                instructions.add(Triple(elements[1].toInt(), elements[3].toInt() - 1, elements[5].toInt() - 1))

            }
        }
    }


    fun part1() {
        readinput()

        instructions.forEach {
            for (i in 1 .. it.first){
                var mover = stacks[it.second].last()
                stacks[it.second].removeLast();
                stacks[it.third].add(mover)
            }
        }

        for (i in 0..8){
            print(stacks[i].last())
        }
    }

    fun part2() {
        readinput()

        instructions.forEach {
            var movers = stacks[it.second].takeLast(it.first);
            stacks[it.second] = stacks[it.second].dropLast(it.first).toMutableList()
            stacks[it.third].addAll(movers)

        }

        for (i in 0..8){
            print(stacks[i].last())
        }

    }
}
