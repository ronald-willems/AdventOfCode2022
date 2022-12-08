package Day8

import java.io.File
import java.util.regex.Pattern
import kotlin.math.max

object Day8 {
    var grid = mutableListOf<IntArray>()
    var width = 0;
    var height = 0

    fun readinput(){

        File("src/main/resources/Day8/TestInput.txt").forEachLine { line ->

            if (line.length> width) width = line.length
            var nrsLine = IntArray(line.length);
            for (i in 0.. line.length-1){
                nrsLine[i]=line[i].toString().toInt()
            }

            grid.add(nrsLine)

            height++


        }


    }
    fun treeHeight(h:Int, w:Int): Int{
        if (h<0 || h> height-1) return -1
        if (w<0 || w> width-1) return -1
        return grid[h][w];

    }

    fun visibleFromTop(h:Int, w: Int): Boolean {
        for (hi in 0..h-1){
            if (treeHeight(hi,w)>=treeHeight(h,w)) return false
        }

        return true
    }
    fun visibleFromBottom(h:Int, w: Int): Boolean {
        for (hi in h+1..height-1){
            if (treeHeight(hi,w)>=treeHeight(h,w)) return false
        }

        return true
    }
    fun visibleFromLeft(h:Int, w: Int): Boolean {
        for (wi in 0..w-1){
            if (treeHeight(h,wi)>=treeHeight(h,w)) return false
        }

        return true
    }
    fun visibleFromRight(h:Int, w: Int): Boolean {
        for (wi in w+1..width-1){
            if (treeHeight(h,wi)>=treeHeight(h,w)) return false
        }

        return true
    }



    fun part1() {
        readinput()
        var nrVisible = 0

        for (h in 0..height-1){
            for (w in 0..width-1){
                if (visibleFromTop(h,w) || visibleFromBottom(h,w) || visibleFromLeft(h,w)|| visibleFromRight(h,w)) nrVisible++
            }
        }
        println(nrVisible)
    }

    fun treeHeight2(h:Int, w:Int): Int{
        if (h<0 || h> height-1) return 10
        if (w<0 || w> width-1) return 10
        return grid[h][w];

    }

    fun nrVisibleFromTop(h:Int, w: Int): Int {

        if (h==0) return 0;
        var result = 0
        for (hi in h-1 downTo 0){
            if (treeHeight2(hi,w)<=treeHeight2(h,w))  result++
            if (treeHeight2(hi,w)>=treeHeight2(h,w)) return result
        }

        return result;
    }
    fun nrVisibleFromBottom(h:Int, w: Int): Int {

        if (h==height-1) return 0;
        var result = 0
        for (hi in h+1..height-1){
            if (treeHeight2(hi,w)<=treeHeight2(h,w))  result++
            if (treeHeight2(hi,w)>=treeHeight2(h,w)) return result
        }

        return result;
    }
    fun nrVisibleFromLeft(h:Int, w: Int): Int {
        if (w==0) return 0;
        var result = 0
        for (wi in w-1 downTo 0){
            if (treeHeight2(h,wi)<=treeHeight2(h,w))  result++
            if (treeHeight2(h,wi)>=treeHeight2(h,w)) return result
        }

        return result;
    }
    fun nrVisibleFromRight(h:Int, w: Int): Int {

        if (w==width-1) return 0;
        var result = 0
        for (wi in w+1..width-1){
            if (treeHeight2(h,wi)<=treeHeight2(h,w))  result++
            if (treeHeight2(h,wi)>=treeHeight2(h,w)) return result
        }

        return result;
    }


    fun scenicScore(h:Int, w:Int):Int{
        return nrVisibleFromTop(w,h)* nrVisibleFromBottom(w,h)* nrVisibleFromLeft(w,h)* nrVisibleFromRight(w,h)


    }

    fun part2() {
        readinput()
        var maxScenicScore = 0
        for (h in 0..height-1){
            for (w in 0..width-1){
                var scenicScore = scenicScore(h,w)
                if (scenicScore> maxScenicScore) maxScenicScore = scenicScore


            }
        }
        println(maxScenicScore)


    }
}