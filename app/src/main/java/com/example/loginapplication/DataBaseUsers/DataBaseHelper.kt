package com.example.loginapplication.DataBaseUsers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.loginapplication.Objects.Usuario

class DataBaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {


        val usersTable =
            ("CREATE TABLE " + TABLE_USERS + " (" + ID_USERS + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NAME_USERS + " TEXT," + LASTNAME_USERS + " TEXT," + EMAIL_USERS + " TEXT," +
                    PASS_USERS + " TEXT," + TELL_USERS + " TEXT" + ")")

        db!!.execSQL(usersTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldversion: Int, newversion: Int) {

        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun AddUser(nombre: String, apellido: String, email: String, pass: String, telefono: String) {

        val db = this.writableDatabase
        val datos = ContentValues()

        datos.put(NAME_USERS, nombre)
        datos.put(LASTNAME_USERS, apellido)
        datos.put(EMAIL_USERS, email)
        datos.put(PASS_USERS, pass)
        datos.put(TELL_USERS, telefono)

        db.insert(TABLE_USERS, null, datos)
        db.close()

    }

    fun getData(): Cursor? {
        val db = this.readableDatabase

        val columns = arrayOf(ID_USERS,NAME_USERS, LASTNAME_USERS, EMAIL_USERS, TELL_USERS)


        val cursor = db.query(
            TABLE_USERS,
            columns,
            null,
            null,
            null,
            null,
            null
        )

        return cursor
    }

    fun getDataPorId(id: Int) : Cursor{

        val db = this.readableDatabase

        val columns = arrayOf(ID_USERS,NAME_USERS, LASTNAME_USERS, EMAIL_USERS, TELL_USERS)
        val selection = "$ID_USERS = ?" //where clausula
        val selectionArg =  arrayOf(id.toString()) // valores del where

        val cursor = db.query(
            TABLE_USERS,
            columns,
            selection,
            selectionArg,
            null,
            null,
            null
        )
        return cursor
    }

    fun deleteUser(id: Int) {

        val db = this.writableDatabase
        val selection = "$ID_USERS = ?"

        db.delete(TABLE_USERS, selection, arrayOf(id.toString()))
        db.close()
    }

    fun updateUser(id: Int, nombre: String, apellido: String, email: String, pass: String, telefono: String) {

        val db = this.writableDatabase
        val datos = ContentValues()

        datos.put(NAME_USERS, nombre)
        datos.put(LASTNAME_USERS, apellido)
        datos.put(EMAIL_USERS, email)
        datos.put(PASS_USERS, pass)
        datos.put(TELL_USERS, telefono)

        val selection = "$ID_USERS = ?"

        db.update(TABLE_USERS,datos,selection, arrayOf(id.toString()))

        db.close()

    }

    fun loguinData(emailUsu: String): Cursor? {
        val db = this.readableDatabase

        val columns = arrayOf(EMAIL_USERS, PASS_USERS, NAME_USERS, LASTNAME_USERS)
        val selection = "$EMAIL_USERS = ?" //where clausula
        val selectionArg = arrayOf(emailUsu) // valores del where

        val cursor = db.query(
            TABLE_USERS,
            columns,
            selection,
            selectionArg,
            null,
            null,
            null
        )

        return cursor
    }

    companion object {

        private val DATABASE_NAME = "db_registroUser"
        private val DATABASE_VERSION = 19


        // Tabla Users
        private val TABLE_USERS = "users"
        private val ID_USERS = "_id"
        private val NAME_USERS = "nombre"
        private val LASTNAME_USERS = "apellido"
        private val EMAIL_USERS = "email"
        private val PASS_USERS = "password"
        private val TELL_USERS = "telefono"


    }


}