package uz.megasoft.newapp.data.repositoryImpl

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import uz.megasoft.newapp.domain.model.User
import uz.megasoft.newapp.domain.repository.UserRepository

class UserRepositoryImpl (
    private val firestore: FirebaseFirestore
) : UserRepository {

    override suspend fun saveUser(user: User): Boolean {
        return try {
            firestore.collection("users")
                .document(user.uid)
                .set(user)
                .await() // Firebase Firestore operatsiyasini asinxron kutilishini ta'minlash
            true
        } catch (e: Exception) {
            Log.d("TAG", "saveUser: $e")
            false
        }
    }

    override suspend fun getUser(uid: String): User? {
        return try {
            val document = firestore.collection("users")
                .document(uid)
                .get()
                .await()
            document.toObject(User::class.java)
        } catch (e: Exception) {
            null
        }
    }
}
