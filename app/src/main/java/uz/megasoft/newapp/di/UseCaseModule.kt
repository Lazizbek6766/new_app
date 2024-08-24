package uz.megasoft.newapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.megasoft.newapp.domain.repository.AuthRepository
import uz.megasoft.newapp.domain.repository.TableRepository
import uz.megasoft.newapp.domain.repository.UserRepository
import uz.megasoft.newapp.domain.use_case.auth.AuthUseCases
import uz.megasoft.newapp.domain.use_case.auth.LoginUseCase
import uz.megasoft.newapp.domain.use_case.auth.RegisterUseCase
import uz.megasoft.newapp.domain.use_case.tables.AddTableUseCase
import uz.megasoft.newapp.domain.use_case.tables.GetTablesUseCase
import uz.megasoft.newapp.domain.use_case.tables.TableUseCases
import uz.megasoft.newapp.domain.use_case.user.GetUserUseCase
import uz.megasoft.newapp.domain.use_case.user.SaveUserUseCase
import uz.megasoft.newapp.domain.use_case.user.UserUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideAuthsUseCase(repository: AuthRepository): AuthUseCases {
        return AuthUseCases(
            loginUseCase = LoginUseCase(repository),
            registerUseCase = RegisterUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideUserUseCase(repository: UserRepository): UserUseCases {
        return UserUseCases(
            getUserUseCase = GetUserUseCase(repository),
            saveUserUseCase = SaveUserUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideTaableUseCase(repository: TableRepository): TableUseCases {
        return TableUseCases(
            addTableUseCase = AddTableUseCase(repository),
            getTablesUseCase = GetTablesUseCase(repository)

        )
    }
}