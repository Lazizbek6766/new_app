package uz.megasoft.newapp.domain.use_case.user

import uz.megasoft.newapp.domain.model.User
import uz.megasoft.newapp.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(uid: String): User? {
        return repository.getUser(uid)
    }
}
