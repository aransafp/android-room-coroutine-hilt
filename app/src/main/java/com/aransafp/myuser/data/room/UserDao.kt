package com.aransafp.myuser.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aransafp.myuser.data.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getAllUser(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}