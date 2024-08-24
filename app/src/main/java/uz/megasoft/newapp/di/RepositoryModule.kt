package uz.megasoft.newapp.di


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.megasoft.newapp.data.repositoryImpl.AuthRepositoryImpl
import uz.megasoft.newapp.data.repositoryImpl.TableTableRepositoryImpl
import uz.megasoft.newapp.data.repositoryImpl.UserRepositoryImpl
import uz.megasoft.newapp.domain.repository.AuthRepository
import uz.megasoft.newapp.domain.repository.TableRepository
import uz.megasoft.newapp.domain.repository.UserRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository(auth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(auth)
    }

    @Provides
    @Singleton
    fun provideUserRepository(firestore: FirebaseFirestore): UserRepository {
        return UserRepositoryImpl(firestore)
    }

    @Provides
    @Singleton
    fun provideRepository(firestore: FirebaseFirestore): TableRepository {
        return TableTableRepositoryImpl(firestore)
    }

}