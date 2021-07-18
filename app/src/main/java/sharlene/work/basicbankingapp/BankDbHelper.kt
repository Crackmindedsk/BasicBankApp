package sharlene.work.basicbankingapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BankDbHelper(context: Context?) : SQLiteOpenHelper(context, databaseName, null, version) {


    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE =
            "CREATE TABLE user (_id INTEGER, name TEXT, email VARCHAR PRIMARY KEY, balance INTEGER, phone TEXT, account VARCHAR)"
        db?.execSQL(CREATE)
        val createTransact =
            "CREATE TABLE transact (transact_id INTEGER, fromName TEXT, toName TEXT, amount INTEGER, status TEXT)"
        db?.execSQL(createTransact)

        insert("Sharlene", "sharlene@gmail.com", 10000, "9363803949", "XXXXXXXXXXXX4758", db)
        insert("Sumit", "sumit23@yahoo.com", 10000, "3467892547", "XXXXXXXXXXXX2929", db)
        insert("Mayuri", "mayuri89@gmail.com", 6000, "7349006729", "XXXXXXXXXXXX3609", db)
        insert("Digvijay", "kdigvijay@gmail.com", 10000, "7902573940", "XXXXXXXXXXXX2470", db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldV: Int, newV: Int) {
        db?.execSQL("DROP TABLE IF EXISTS user")
        db?.execSQL("DROP TABLE IF EXISTS transact")
        onCreate(db)
    }

    fun ReadData(): Cursor {
        val db: SQLiteDatabase = writableDatabase
        return db.rawQuery("SELECT * FROM user", null)
    }

    fun readAData(Email: String): Cursor? {
        val db: SQLiteDatabase = this.writableDatabase
        val select = "SELECT * FROM user WHERE email = '$Email'"
        return db.rawQuery(select, null)
    }

    fun readSelect(Email: String): Cursor? {
        val db = writableDatabase
        val select = "SELECT * FROM user WHERE email !='$Email'"
        return db.rawQuery(select, null)
    }

    fun update(Email: String, Amount: String) {
        val db = this.writableDatabase
        val select = "UPDATE user SET balance = '$Amount' WHERE email = '$Email'"
        db.execSQL(select)
    }

    private fun insert(
        name: String,
        email: String,
        balance: Int,
        phone: String,
        account: String,
        db: SQLiteDatabase?
    ) {
        val values: ContentValues = ContentValues().apply {
            put("name", name)
            put("email", email)
            put("phone", phone)
            put("account", account)
            put("balance", balance)
        }
        db?.insert("user", null, values)
    }

    fun insertTransferData(from: String?, to: String, amount: Int, status: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("fromName", from)
            put("toName", to)
            put("amount", amount)
            put("status", status)
        }
        db?.insert("transact", null, values)
    }

    fun readTransfer(): Cursor? {
        val db: SQLiteDatabase = this.writableDatabase
        val select = "SELECT * FROM transact"
        return db.rawQuery(select, null)
    }

    companion object {
        private const val databaseName = "Bank"
        private const val version = 1
    }
}