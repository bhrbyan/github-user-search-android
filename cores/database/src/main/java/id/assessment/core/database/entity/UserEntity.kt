package id.assessment.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_entity")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "user_id") val userId: Int,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String,
)