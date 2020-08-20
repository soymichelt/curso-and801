package com.soymichel.sqliteexample

import android.content.Context
import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.ImageView
import android.widget.TextView

class StudentCursorAdapter(context: Context, c: Cursor):CursorAdapter(context, c, false) {
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return View.inflate(context, R.layout.list_line_student, null)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val studentName = view?.findViewById<View>(R.id.studentName) as TextView
        val facultyName = view?.findViewById<View>(R.id.facultyName) as TextView
        val imageView = view?.findViewById<View>(R.id.imageView) as ImageView

        studentName.text = cursor?.getString(cursor.getColumnIndex("name"))

        facultyName.text = cursor?.getString(cursor.getColumnIndex("faculty"))

        when (facultyName.text.toString()) {
            "Engineering" -> {
                imageView.setImageResource(R.drawable.ic_engineering)
            }
            "Business" -> {
                imageView.setImageResource(R.drawable.ic_business)
            }
            "Arts" -> {
                imageView.setImageResource(R.drawable.ic_arts)
            }
            else -> {
                imageView.setImageResource(R.mipmap.ic_launcher)
            }

        }
    }

}