package com.example.database.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.database.model.Student


@Dao
interface StudentDao {
    @Insert(onConflict = REPLACE)
    fun insertStudent(student: Student)

    @Query("DELETE FROM MyStudent WHERE id LIKE :id ")
     fun deleteStudent(id : String)

    @Update()
    fun UpdateStudent(student: Student?)

    @Query("select * from MyStudent")
    fun getAllStudent() : List<Student>?
}