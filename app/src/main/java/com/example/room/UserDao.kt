package com.example.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun addUser(user : User)

    @Query("Select * from user")
    fun getUsers() : List<User>

    @Query("Delete from user")
    fun DeleteAll()

    @Delete
    fun deleteUser(user: User)

    @Query("update user set name = :name where id = :id")
    fun update(id : Int, name : String)
}