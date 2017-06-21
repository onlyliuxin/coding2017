package cn.net.pikachu.srp.dao.impl

import cn.net.pikachu.srp.dao.ProductDao
import cn.net.pikachu.srp.domain.Product

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
