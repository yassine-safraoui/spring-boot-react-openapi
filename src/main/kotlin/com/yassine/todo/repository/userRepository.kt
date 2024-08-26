package com.yassine.todo.repository

import com.yassine.todo.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*

@Repository
interface userRepository : JpaRepository<User, Long> {
    // Method to find users by name
    fun findByName(name: String): List<User>

    // Method to find a user by email
    fun findByEmail(email: String): User?

    // Method to find users by partial email match
    fun findByEmailContaining(partialEmail: String): List<User>
}


@Service
class UserService(private val userRepository: userRepository) {
    fun saveUser(user: User): User {
        return userRepository.save(user)
    }

    fun getUser(id: Long): Optional<User> {
        return userRepository.findById(id)
    }

    fun findUserByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }

    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }
}