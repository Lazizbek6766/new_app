package uz.megasoft.newapp.domain.use_case.auth

import com.google.firebase.auth.FirebaseUser
import uz.megasoft.newapp.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<FirebaseUser> {
        return repository.register(email, password)
    }
}