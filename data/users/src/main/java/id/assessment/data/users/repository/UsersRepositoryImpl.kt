package id.assessment.data.users.repository

import id.assessment.data.users.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(): UsersRepository {
    override fun searchUsers(): Flow<List<User>> {
        TODO("Not yet implemented")
    }
}