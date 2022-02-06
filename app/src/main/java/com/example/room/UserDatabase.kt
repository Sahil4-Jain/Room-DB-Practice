package com.example.room

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [User::class],
    version = 2,
)
abstract class UserDatabase : RoomDatabase() {

     abstract fun getDao() : UserDao

     companion object{
         val migration1_2 = object : Migration(1,2){
             override fun migrate(database: SupportSQLiteDatabase) {
                 database.execSQL("drop table User;")
                 database.execSQL("create table User(" +
                         "id INTEGER primary key autoincrement," +
                         "name text not null," +
                         "age int not null);")
             }

         }

         @Volatile
         private var instance : UserDatabase? = null

         operator fun invoke(context : Context) = instance?: synchronized(this){
                instance ?: getDatabase(context).also {
                    instance = it
                }
         }

         private fun getDatabase(context: Context) = Room
                     .databaseBuilder(
                         context.getApplicationContext(),
                         UserDatabase::class.java,
                         "userdatabase"
                     ).allowMainThreadQueries()
                      .addMigrations(migration1_2)
                      .build()
     }
}
