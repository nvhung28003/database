package com.example.database.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.database.R
import com.example.database.adapter.StudentAdapter
import com.example.database.model.Student
import com.example.database.room.AppDatabase
import com.example.database.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_student.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    lateinit var studentAdapter: StudentAdapter
    private var listStudent: ArrayList<Student> = ArrayList()

    private lateinit var viewmodel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        initAdapter()
        getDataFromDataBase()

        btn_add.setOnClickListener { v ->
            viewmodel.addStudent(
                edt_id.text.toString(),
                edt_name.text.toString(),
                edt_sex.text.toString()
            )
            getDataFromDataBase()

        }
        btn_delete.setOnClickListener { v ->

            viewmodel.deleteStudent(edt_id.text.toString())
            getDataFromDataBase()


        }
        btn_edit.setOnClickListener { v ->

            viewmodel.ediStudent(
                edt_id.text.toString(),
                edt_name.text.toString(),
                edt_sex.text.toString()
            )
            getDataFromDataBase()


        }
    }


    fun getDataFromDataBase() {
        viewmodel.getAllStudent()
        viewmodel.requestLiveData.observe(this, Observer { t ->
            listStudent.clear()
            listStudent.addAll(t);
            studentAdapter.notifyDataSetChanged()
        })

    }

    fun initAdapter() {
        var layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcv_student.layoutManager = layoutManager

        studentAdapter = StudentAdapter(this, listStudent) { student ->
            run {
                edt_id.setText(student.id)
                edt_name.setText(student.name)
                edt_sex.setText(student.sex)

            }
        }
        rcv_student.adapter = studentAdapter

    }

}
