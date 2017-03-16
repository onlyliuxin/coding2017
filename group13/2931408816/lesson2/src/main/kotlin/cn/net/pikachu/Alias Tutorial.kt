package cn.net.pikachu

import com.thoughtworks.xstream.XStream
import java.util.ArrayList
import com.thoughtworks.xstream.converters.SingleValueConverter





/**
 * Created by pikachu on 17-3-2.
 */
fun main(args: Array<String>) {
    val list = mutableListOf<Entry>()
    list.add(Entry("first","My first blog entry."))
    list.add(Entry("tutorial", "Today we have developed a nice alias tutorial. Tell your friends! NOW!"))

    val teamBlog = Blog(Author("Guilherme Silveira"),list)

    val xstream = XStream()
    xstream.alias("blog",Blog::class.java)
    xstream.alias("entry",Entry::class.java)
//    xstream.aliasField("author",Blog::class.java,"writer")

    xstream.addImplicitCollection(Blog::class.java,"entries")

    xstream.useAttributeFor(Blog::class.java,"author")
    xstream.registerConverter(AuthorConverter())

    // 更改包名
//    xstream.aliasPackage("my.company","cn.net.pikachu")

    println(xstream.toXML(teamBlog))

}
data class Entry(
        var title:String,
        var description:String
)
data class Author(
        var name:String
)
data class Blog(
        var author:Author,
        val entries:MutableList<Entry> = mutableListOf()
){
    fun add(entry: Entry){
        entries.add(entry)
    }
}

class AuthorConverter : SingleValueConverter {

    override fun toString(obj: Any): String {
        return (obj as Author).name
    }

    override fun fromString(name: String): Any {
        return Author(name)
    }

    override fun canConvert(type: Class<*>): Boolean {
        return type == Author::class.java
    }

}