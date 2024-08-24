package uz.megasoft.newapp.domain.repository

import uz.megasoft.newapp.domain.model.User

interface UserRepository {
    suspend fun saveUser(user: User): Boolean
    suspend fun getUser(uid: String): User?
}
