package id.assessment.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import id.assessment.core.database.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user_entity WHERE user_id = :userId")
    suspend fun getUsers(userId: Int): List<UserEntity>

    @Insert
    suspend fun insertUser(userEntity: UserEntity)

    @Delete
    suspend fun deleteUser(userEntity: UserEntity)
}