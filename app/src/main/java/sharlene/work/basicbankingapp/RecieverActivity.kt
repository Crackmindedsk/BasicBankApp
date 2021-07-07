package sharlene.work.basicbankingapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sharlene.work.basicbankingapp.model.Data
import sharlene.work.basicbankingapp.model.RecieverAdapter
import sharlene.work.basicbankingapp.BankDbHelper

class RecieverActivity:AppCompatActivity() {
    var recieverList:MutableList<Data> =ArrayList()
    var Name:String?=null
    var Email:String?=null
    var recieverEmail:String?=null
    var recieverName:String?=null
    var recieverBalance:Int?=null
    var balance:Int?=null
    var transferAmt:Int?=null

    var adapter:RecieverAdapter?=null
    var viewList:RecyclerView?=null
    val layoutManager:RecyclerView.LayoutManager=LinearLayoutManager(this)
    private val listener:RecyclerView.RecyclerListener?=null
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id= item.itemId
        if(id==R.id.action_history){
            val intent=Intent(this,TransactionActivity::class.java)
            startActivity(intent)
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reciever_activity)
        viewList=findViewById(R.id.reciever_list_view)
        viewList!!.layoutManager=layoutManager
        val db = BankDbHelper(this)
        val bundle=intent.extras
        if (bundle!=null){
            Name=bundle.getString("name")
            Email=bundle.getString("email")
            transferAmt=bundle.getInt("transfer amount")
            balance=bundle.getInt("Current Balance")
            showData(Email)
        }
        adapter= RecieverAdapter(this,recieverList,listener!!)
        viewList!!.adapter=adapter
    }
    private fun showData(Email:String?){
        val db=BankDbHelper
        recieverList.clear()
        val cursor=db.readSelect(Email)
    }

}


