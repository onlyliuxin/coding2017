package cn.net.pikachu.dao.impl

import cn.net.pikachu.dao.ProductDao
import cn.net.pikachu.domain.Product

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

class ProductDaoInFileImpl(val file:String) : ProductDao {
    override fun find(): List<Product> {
        val list = ArrayList<Product>()
        val stream = Files.newInputStream(Paths.get(file))
        stream.buffered().reader().use { reader ->
            reader.readLines().forEach {
                val s = it.split(" ")
                val product = Product()

                product.id = s[0]
                product.name = s[1]

                list.add(product)
            }
        }
        return list
    }
}
