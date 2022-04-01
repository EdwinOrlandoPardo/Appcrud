package DataBaseUsers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {




    override fun onCreate(db: SQLiteDatabase?) {


        val usersTable = ("CREATE TABLE "+ TABLE_USERS + " ("+ ID_USERS + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                NAME_USERS + "TEXT, " + LASTNAME_USERS + "TEXT, " + EMAIL_USERS + "TEXT, "+
                PASS_USERS + "TEXT, " + TELL_USERS + "TEXT" +")")

        //db!!.execSQL(singUpTable)
        db!!.execSQL(usersTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldversion: Int, newversion: Int) {

        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun AddUser(nombre : String , apellido : String , email : String, password : String, telefono : Int){

        val datos = ContentValues()

        datos.put(NAME_USERS, nombre)
        datos.put(LASTNAME_USERS, apellido)
        datos.put(EMAIL_USERS,email)
        datos.put(PASS_USERS,password)
        datos.put(TELL_USERS,telefono)

        val db = this.writableDatabase
        db.insert(TABLE_USERS,null,datos)
        db.close()

    }

    companion object{

        private val DATABASE_NAME = "db_registroUser"
        private val DATABASE_VERSION = 1

        // Tabla singUp
        private val TABLE_LOGIN = "singup"
        private val ID_COL = "_id"
        private val EMAIL_COL = "email"
        private val PASS_COL = "password"

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