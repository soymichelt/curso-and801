package com.soymichel.sqliteexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var studentCursorAdapter:StudentCursorAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        loadList()
    }

    private fun initializeViews() {
        faculties_spinner.adapter = ArrayAdapter(this@MainActivity,
            android.R.layout.simple_list_item_1,
            MyApp.mAllFaculties)

        add_student.setOnClickListener {
            if(student_name.text.isBlank()){
                Toast.makeText(this, "Student is missing", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val facultyName = MyApp.mAllFaculties.get(faculties_spinner.selectedItemPosition)
            MyApp.studentDao.insertStudent(student_name.text.toString(),facultyName)
            student_name.setText("")
            //loadList()
        }

    }

    private fun loadList() {
        val allStudents = MyApp.studentDao.selectAllStudents()
        if(allStudents==null) return;

        studentCursorAdapter = StudentCursorAdapter(this@MainActivity, allStudents)
        student_list.adapter = studentCursorAdapter
    }


}