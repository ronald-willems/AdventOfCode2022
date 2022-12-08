package Day7

import java.io.File
import java.util.regex.Pattern

object Day7 {
    var currentDir = Directory("/",0)
    var allDirs = mutableListOf<Directory>()
    var topDir = currentDir

    fun part1() {


        File("src/main/resources/Day7/TestInput.txt").forEachLine { line ->
            if (line != "\$ cd /") { //skip the first line
                val elements = line.split(" ")
                //val elements2 = line.split( Pattern.compile("[,\\-]"))
                if (elements[0][0].isDigit()) currentDir.size+=elements[0].toInt();
                if (elements[0]=="$"){
                    if (elements[1]=="cd") {
                        if (elements[2]==".."){
                            currentDir = currentDir.parent!!
                        }
                        else{
                            var newDir = Directory(elements[2],0,currentDir)
                            currentDir.childs.add(newDir);
                            allDirs.add(newDir)
                            currentDir = newDir
                        }
                    }
                }
            }
        }
        var totalSmall = 0
        allDirs.forEach {
            if (it.size()<=100000) totalSmall+= it.size();
        }
        println(totalSmall)
    }

    fun part2(){
        part1();
        var unused = 70000000 - topDir.size()
        var sizeToFind =  30000000 - unused;
        var smallestFitter = 70000000
        allDirs.forEach {
            if (it.size()<smallestFitter && it.size()>=sizeToFind) smallestFitter = it.size()


        }
        println(smallestFitter)



    }
}