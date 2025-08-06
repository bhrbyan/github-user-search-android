package id.assessment.data.users.repository

import android.util.Log
import id.assessment.core.database.dao.UserDao
import id.assessment.core.database.entity.UserEntity
import id.assessment.core.di.dispatcher.CoreDispatcher
import id.assessment.data.users.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UsersLocalRepository @Inject constructor(
    private val usersDao: UserDao,
    private val dispatcher: CoreDispatcher
) : UsersRepository {

    override suspend fun searchUsers(query: String): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserDetail(userId: Int): Flow<User> {
        TODO("Not yet implemented")
    }

    override suspend fun saveUserDetail(user: User) {
        val userEntity = UserEntity(null, user.userId, user.login, user.avatarUrl ?: "")

        usersDao.insertUser(userEntity)
    }

    override suspend fun getFavoritesUser(userId: Int): Flow<List<User>> = flow {
        val response = usersDao.getUsers(userId)
        val users = response.map {
            User(
                id = it.id,
                userId = it.userId,
                login = it.username,
                avatarUrl = it.avatarUrl,
            )
        }
        emit(users)
    }.catch { e ->
        Log.e("Repo", "Error: ${e.message}")
        emit(emptyList())
    }.flowOn(dispatcher.io)

    override suspend fun deleteUserDetail(user: User) {
        val userEntity = UserEntity(user.id, user.userId, user.login, user.avatarUrl ?: "")

        usersDao.deleteUser(userEntity)
    }
}