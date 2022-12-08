package Day7

class Directory (val name:String, var size:Int, var parent:Directory? =null, var childs:MutableList<Directory> = mutableListOf()) {

    fun size(): Int{

        return childs.sumOf{it.size()} + size

    }


}