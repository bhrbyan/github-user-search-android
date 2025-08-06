package id.assessment.data.users.repository

import id.assessment.data.users.model.User
import id.quranesia.android.core.di.qualifier.LocalSource
import id.quranesia.android.core.di.qualifier.RemoteSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    @LocalSource private val local: UsersRepository,
    @RemoteSource private val remote: UsersRepository,
): UsersRepository {
    override suspend fun searchUsers(query: String): Flow<List<User>> {
        return remote.searchUsers(query)
    }
}