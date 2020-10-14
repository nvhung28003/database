package com.example.database.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.database.model.Student
import com.example.database.room.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var appDatabase: AppDatabase;


     var requestLiveData: MutableLiveData<List<Student>> = MutableLiveData()


    init {
       appDatabase = AppDatabase.getInstance(application)!!
    }

    fun addStudent(id: String, name: String, sex: String) {
        appDatabase.studentDataDao().insertStudent(
            student = Student(id, name, sex)
        )
    }

    fun deleteStudent(id: String) {
        appDatabase.studentDataDao().deleteStudent(id)
    }

    fun ediStudent(id: String, name: String, sex: String) {
        appDatabase.studentDataDao().UpdateStudent(
            student = Student(id, name, sex)
        )
    }

    fun getAllStudent() {
        requestLiveData.postValue( appDatabase.studentDataDao().getAllStudent()!!)
    }


}