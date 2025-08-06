package id.assessment.data.users.repository

import id.assessment.data.users.model.User
import id.quranesia.android.core.di.qualifier.LocalSource
import id.quranesia.android.core.di.qualifier.RemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    @LocalSource private val local: UsersRepository,
    @RemoteSource private val remote: UsersRepository,
) : UsersRepository {

    override suspend fun searchUsers(query: String): Flow<List<User>> {
        return local.searchUsers(query).flatMapConcat { users ->
            if (users.isNotEmpty()) {
                print("GET DATA FROM LOCAL")
                flowOf(users)
            } else {
                remote.searchUsers(query)
                    .flatMapConcat { data ->
                        saveUsers(data)
                        print("GET DATA FROM REMOTE")
                        flowOf(data)
                    }
            }
        }
    }

    override suspend fun getUserDetail(userId: Int): Flow<User> {
        return local.getUserDetail(userId).flatMapConcat { user ->
            if (user.id != null) {
                flowOf(user)
            } else {
                remote.getUserDetail(userId)
            }
        }
    }

    override suspend fun getUsers(): Flow<List<User>> {
        return local.getUsers()
    }

    override suspend fun saveUsers(users: List<User>) {
        local.saveUsers(users)
    }
}