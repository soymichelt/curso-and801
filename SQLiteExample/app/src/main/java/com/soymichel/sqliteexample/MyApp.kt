package com.soymichel.sqliteexample

import android.app.Application

class MyApp:Application() {
    companion object {
        lateinit var studentDao: StudentDao
        val mAllFaculties = arrayOf("Engineering", "Business", "Arts")
    }

    override fun onCreate() {
        super.onCreate()

        studentDao = StudentDao(this.baseContext)
        studentDao.open()
    }
}