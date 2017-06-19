package cn.net.pikachu.service.impl

import cn.net.pikachu.dao.UserDao
import cn.net.pikachu.domain.Product
import cn.net.pikachu.domain.User
import cn.net.pikachu.service.UserService

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
