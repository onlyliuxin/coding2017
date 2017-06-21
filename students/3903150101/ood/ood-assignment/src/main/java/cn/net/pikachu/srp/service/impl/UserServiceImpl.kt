package cn.net.pikachu.srp.service.impl

import cn.net.pikachu.srp.dao.UserDao
import cn.net.pikachu.srp.domain.Product
import cn.net.pikachu.srp.domain.User
import cn.net.pikachu.srp.service.UserService

class UserServiceImpl(val userDao: UserDao) : UserService {
    /**
     * @param product
     * *
     * @return
     */
    override fun getSubscribers(product: Product): List<User>? {
        return userDao.find(product)
    }
}
