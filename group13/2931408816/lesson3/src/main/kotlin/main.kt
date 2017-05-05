import java.io.File
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Created by pikachu on 2017/3/13.
 */

fun main(args: Array<String>) {
    val dir = File("E:/users/pikachu/documents/Tencent Files/2931408816/FileRecv/treat/Input")
    dir.listFiles().forEach {
        if (!it.isDirectory){

            println(it.canonicalPath)
            println(it.absolutePath)
            println(it.parent)
            val dir = File("${it.parent}/temp")
            if (!dir.exists()){
                dir.mkdirs()
            }
            val file = File(dir,it.name)
            if (!file.exists()){
                file.createNewFile()
            }
            val out = PrintWriter(file)
            val stream = Files.newInputStream(Paths.get(it.canonicalPath))
            stream.buffered().use {
                while (it.available()>0){
                    out.print(it.read().toChar())
                    if(it.available()>0){
                        out.print(" ")
                    }
                }
            }

            out.close()
        }
    }
}