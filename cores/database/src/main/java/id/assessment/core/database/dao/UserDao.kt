package id.assessment.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.assessment.core.database.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user_entity")
    suspend fun getUsers(): List<UserEntity>

    @Query("SELECT * FROM user_entity WHERE username LIKE '%' || :query || '%'")
    fun searchUsers(query: String): List<UserEntity>

    @Query("SELECT * FROM user_entity WHERE user_id = :userId")
    suspend fun getUserDetail(userId: Int): UserEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(vararg userEntity: UserEntity)
}