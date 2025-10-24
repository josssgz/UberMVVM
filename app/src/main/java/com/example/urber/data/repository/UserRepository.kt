package com.example.urber.data.repository

import com.example.urber.data.local.User
import com.example.urber.data.local.UserDAO
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDAO: UserDAO) {

    fun getAllUsers(): Flow<List<User>> {
        return userDAO.getAllUsers()
    }

    suspend fun registerUser(user: User) {
        userDAO.registerUser(user)
    }

    suspend fun updateUser(user: User) {
        userDAO.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDAO.deleteUser(user)
    }
}