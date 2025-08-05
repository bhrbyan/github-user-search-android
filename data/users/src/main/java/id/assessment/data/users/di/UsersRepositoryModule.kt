package id.assessment.data.users.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.assessment.data.users.repository.UsersLocalRepository
import id.assessment.data.users.repository.UsersRemoteRepository
import id.assessment.data.users.repository.UsersRepository
import id.assessment.data.users.repository.UsersRepositoryImpl
import id.quranesia.android.core.di.qualifier.LocalSource
import id.quranesia.android.core.di.qualifier.RemoteSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UsersRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindUsersRepository(usersRepository: UsersRepositoryImpl): UsersRepository

    @Binds
    @LocalSource
    abstract fun bindSurahLocalRepository(surahLocalRepository: UsersLocalRepository): UsersRepository

    @Binds
    @RemoteSource
    abstract fun bindSurahRemoteRepository(surahRemoteRepository: UsersRemoteRepository): UsersRepository

}