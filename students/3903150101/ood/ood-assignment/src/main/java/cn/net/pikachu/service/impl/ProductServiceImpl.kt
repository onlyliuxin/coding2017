package cn.net.pikachu.service.impl

import cn.net.pikachu.dao.ProductDao
import cn.net.pikachu.domain.Product
import cn.net.pikachu.service.ProductService

class ProductServiceImpl(val productDao: ProductDao) : ProductService {
    /**
     * @return
     */
    override fun getPromtionProduct(): List<Product> {
        return productDao.find()
    }
}
