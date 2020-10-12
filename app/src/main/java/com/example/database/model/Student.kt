package com.example.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyStudent")
data class Student (
     @PrimaryKey()
     @ColumnInfo(name = "id")
     var id : String,
     @ColumnInfo(name = "name")
     var name : String,
     @ColumnInfo(name = "sex")
     var sex : String
)