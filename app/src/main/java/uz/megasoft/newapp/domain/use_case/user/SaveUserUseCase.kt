package uz.megasoft.newapp.domain.use_case.user

import uz.megasoft.newapp.domain.model.User
import uz.megasoft.newapp.domain.repository.UserRepository

class SaveUserUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User): Boolean {
        return repository.saveUser(user)
    }
}