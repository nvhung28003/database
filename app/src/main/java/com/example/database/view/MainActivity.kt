package com.example.database.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.database.R
import com.example.database.adapter.StudentAdapter
import com.example.database.model.Student
import com.example.database.room.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var appDatabase: AppDatabase;
    lateinit var studentAdapter: StudentAdapter
    private var listStudent: ArrayList<Student> =  ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appDatabase = AppDatabase.getInstance(this)!!
        initAdapter()
        getDataFromDataBase()

        btn_add.setOnClickListener { v ->
            run {
                appDatabase.studentDataDao().insertStudent(
                    student = Student(
                        edt_id.text.toString(),
                        edt_name.text.toString(),
                        edt_sex.text.toString()
                    )
                )
                getDataFromDataBase()
            }
        }
        btn_delete.setOnClickListener { v ->
            edt_id.text?.let {
                appDatabase.studentDataDao().deleteStudent(it.toString())
            }

            getDataFromDataBase()
        }
        btn_edit.setOnClickListener { v ->
            appDatabase.run {
                studentDataDao().UpdateStudent(
                        student = Student(
                            edt_id.text.toString(),
                            edt_name.text.toString(),
                            edt_sex.text.toString()
                        )
                    )
                getDataFromDataBase()
            }

        }
    }

    fun getDataFromDataBase() {
        listStudent.clear()
        Log.e("kevin", "size: "+appDatabase.studentDataDao().getAllStudent()?.size )
        appDatabase.studentDataDao().getAllStudent()?.let { listStudent.addAll(it) }
        studentAdapter.notifyDataSetChanged()
    }

    fun initAdapter() {
        var layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcv_student.layoutManager = layoutManager

        studentAdapter = StudentAdapter(this, listStudent){
            student ->
            run {
                edt_id.setText(student.id)
                edt_name.setText(student.name)
                edt_sex.setText(student.sex)

            }
        }
        rcv_student.adapter = studentAdapter

        }

    }
