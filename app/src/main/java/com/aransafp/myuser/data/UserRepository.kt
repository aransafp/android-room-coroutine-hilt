package com.aransafp.myuser.data

import androidx.lifecycle.LiveData
import com.aransafp.myuser.data.entity.User
import com.aransafp.myuser.data.room.UserDao

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.getAllUser()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun delete(user: User) {
        userDao.deleteUser(user)
    }
}