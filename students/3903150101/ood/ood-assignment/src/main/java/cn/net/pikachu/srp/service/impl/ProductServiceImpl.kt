package cn.net.pikachu.srp.service.impl

import cn.net.pikachu.srp.dao.ProductDao
import cn.net.pikachu.srp.domain.Product
import cn.net.pikachu.srp.service.ProductService

class ProductServiceImpl(val productDao: ProductDao) : ProductService {
    /**
     * @return
     */
    override fun getPromtionProduct(): List<Product> {
        return productDao.find()
    }
}
