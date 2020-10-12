package com.example.database.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.database.R
import com.example.database.model.Student

class StudentAdapter(private  val context: Context, private val listStudent : List<Student>, private val callback: (student: Student)-> Unit) : RecyclerView.Adapter<StudentAdapter.ViewStudentHolder>() {


    class ViewStudentHolder(itemview: View?) : RecyclerView.ViewHolder(itemview!!) {
        var idTextView : TextView =  itemView!!.findViewById(R.id.txt_id);
        var nameTextView : TextView =  itemView!!.findViewById(R.id.txt_name);
        var sexTextView : TextView =  itemView!!.findViewById(R.id.txt_sex);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewStudentHolder =
        ViewStudentHolder(
            LayoutInflater.from(context).inflate(R.layout.item_student, parent, false)
        );

    override fun getItemCount(): Int = listStudent.size

    override fun onBindViewHolder(holder: ViewStudentHolder, position: Int) {
        val item: Student = listStudent.get(position)
        holder.idTextView.setText(item.id)
        holder.nameTextView.setText(item.name)
        holder.sexTextView.setText(item.sex)
        holder.itemView.setOnClickListener { v -> callback.invoke(item) }
    }

}