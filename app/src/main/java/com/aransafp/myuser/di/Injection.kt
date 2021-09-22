package com.aransafp.myuser.di

import android.content.Context
import com.aransafp.myuser.data.UserRepository
import com.aransafp.myuser.data.room.UserDatabase

object Injection {

    fun provideRepository(context: Context): UserRepository {

        val database = UserDatabase.getInstance(context).userDao()

        return UserRepository(database)
    }

}