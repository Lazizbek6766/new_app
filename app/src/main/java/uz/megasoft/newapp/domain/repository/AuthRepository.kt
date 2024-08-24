package uz.megasoft.newapp.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun login(email: String, password: String): Result<FirebaseUser>
    suspend fun register(email: String, password: String): Result<FirebaseUser>
    suspend fun getCurrentUser(): FirebaseUser?
    suspend fun logout()

}