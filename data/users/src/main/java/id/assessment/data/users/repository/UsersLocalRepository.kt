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

    override suspend fun searchUsers(query: String): Flow<List<User>> = flow {
        val response = usersDao.searchUsers(query)
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

    override suspend fun getUserDetail(userId: Int): Flow<User> = flow<User> {
        val entity = usersDao.getUserDetail(userId)
        val user = User(
            id = entity.id,
            userId = entity.userId,
            login = entity.username,
            avatarUrl = entity.avatarUrl,
        )
        emit(user)
    }.catch { e ->
        Log.e("Repo", "Error: ${e.message}")
    }
        .flowOn(dispatcher.io)

    override suspend fun getUsers(): Flow<List<User>> = flow {
        val response = usersDao.getUsers()
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

    override suspend fun saveUsers(users: List<User>) {
        val userEntity = users.map { user ->
            UserEntity(
                user.id,
                user.userId,
                user.login,
                user.avatarUrl ?: "",
            )
        }

        usersDao.insertUsers(*userEntity.toTypedArray())
    }
}