package com.soymichel.sqliteexample

import android.content.ContentValues
import android.content.Context
import android.content.ContextWrapper
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class StudentDao(context: Context): ContextWrapper(context) {

    companion object {
        private const val DATABASE_NAME: String = "students"
        private const val DATABASE_VERSION = 1
    }

    private var dbHelpler: DbHelper
    private var sqliteDatabase: SQLiteDatabase? = null

    init {
        dbHelpler = DbHelper(context, DATABASE_NAME, DATABASE_VERSION)
    }

    fun open() {
        if (sqliteDatabase == null) {
            sqliteDatabase = dbHelpler.writableDatabase
        }
    }

    fun findById(id: Long): Cursor? {
        if (sqliteDatabase == null) {
            open()
        }

        var cursor = sqliteDatabase?.query(
            "students",
            null, "_id=?", arrayOf(id.toString()), null, null, null
        )

        return cursor
    }

    fun insertStudent(name: String, faculty: String): Long {
        if (sqliteDatabase == null) {
            open()
        }

        val cv = ContentValues()
        cv.put("name", name)
        cv.put("faculty", faculty)

        sqliteDatabase?.beginTransaction();
        try {
            val result = sqliteDatabase?.insertOrThrow("students", null, cv) ?: -1
            sqliteDatabase?.setTransactionSuccessful();

            return result
        } catch (e:Exception){
            throw e;
        } finally {
            sqliteDatabase?.endTransaction();
        }
    }

    fun selectAllStudents(): Cursor? {
        if (sqliteDatabase == null) {
            open()
        }

        var cursor = sqliteDatabase?.query(
            "students",
            null, null, null, null, null, null
        )

        return cursor
    }

    fun update(name:String, faculty:String, id: Long): Int {
        if (sqliteDatabase == null) {
            open()
        }

        val cv = ContentValues()
        cv.put("name", name)
        cv.put("faculty", faculty)

        return sqliteDatabase?.update("students", cv , "_id=?", arrayOf(id.toString())) ?: -1
    }

    fun deleteById(id: Long): Int {
        if (sqliteDatabase == null) {
            open()
        }

        return sqliteDatabase?.delete("students", "_id=?", arrayOf(id.toString())) ?: -1
    }

    inner class DbHelper(context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {

        /**
         * ANDROID lo ejecuta solito la primera vez que requiero la BD
         */
        override fun onCreate(db: SQLiteDatabase) {
            val query =
                "CREATE TABLE students(_id integer primary key autoincrement, name text, faculty text);"
            db.execSQL(query)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            val query = "DROP TABLE IF EXISTS students;"
            db.execSQL(query)
            onCreate(db)
        }

    }

}