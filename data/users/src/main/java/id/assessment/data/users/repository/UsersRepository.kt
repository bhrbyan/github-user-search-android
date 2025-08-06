package id.assessment.data.users.repository

import id.assessment.data.users.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun searchUsers(query: String): Flow<List<User>>
}