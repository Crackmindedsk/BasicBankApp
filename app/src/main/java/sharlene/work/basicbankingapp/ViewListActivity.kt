package sharlene.work.basicbankingapp

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sharlene.work.basicbankingapp.model.BankAdapter
import sharlene.work.basicbankingapp.model.Data
import sharlene.work.basicbankingapp.model.RecieverAdapter

class ViewListActivity:AppCompatActivity() {
    var dbHelper:BankDbHelper ?=null
    var Name:ArrayList<String> ?=null
    var Email:ArrayList<String> ?=null
    var Balance:ArrayList<Int> ?=null
    var Phone:ArrayList<String> ?=null
    var Account:ArrayList<String> ?=null
    var adapter:BankAdapter ?=null
    val userlist:List<Data> ?=null
    private var listener:RecieverAdapter.RecyclerviewClickListener ?=null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if (id==R.id.action_history){
            val intent=Intent(this,TransactionActivity::class.java)
            startActivity(intent)
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_list_activity)
        val viewList:RecyclerView=findViewById(R.id.view_list)
        dbHelper= BankDbHelper(this)
        Name= ArrayList()
        Email= ArrayList()
        Balance= ArrayList()
        Phone= ArrayList()
        Account= ArrayList()
        storeData()
        setOnClickListener()
        adapter= BankAdapter(this,Name!!,Email!!,Balance!!,listener)
        //animation
        viewList.adapter= adapter
        viewList.layoutManager=LinearLayoutManager(this)



    }

    fun storeData(){
        val db:SQLiteDatabase= dbHelper!!.readableDatabase
        val values:ContentValues= ContentValues()
        val cursor=db.rawQuery("SELECT name, email, balance, phone, account FROM user", arrayOf())
        cursor?.moveToFirst()

        do {
            val name:String=cursor.getString(0)
            val balance:Int=cursor.getInt(2)
            val email:String=cursor.getString(1)
            val phone:String=cursor.getString(3)
            val account:String=cursor.getString(4)
            Name!!.add(name)
            Email!!.add(email)
            Balance!!.add(balance)
            Account!!.add(account)
            Phone!!.add(phone)
        }while (cursor.moveToNext())
    }
    private fun setOnClickListener(){
        listener=object :RecieverAdapter.RecyclerviewClickListener{
            override fun onClick(view: View?, position: Int) {
                val intent=Intent(applicationContext,ProfileActivity::class.java)
                intent.putExtra("name",Name!![position])
                intent.putExtra("email",Email!![position])
                intent.putExtra("balance",Balance!![position])
                intent.putExtra("account",Account!![position])
                intent.putExtra("phone",Phone!![position])
                startActivity(intent)
            }
        }
    }
}