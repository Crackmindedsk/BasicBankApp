package sharlene.work.basicbankingapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sharlene.work.basicbankingapp.model.Data
import sharlene.work.basicbankingapp.model.TransactionAdapter

class TransactionActivity :AppCompatActivity() {
    var historyList:MutableList<Data> =ArrayList()
    lateinit var recyclerView:RecyclerView
    var historyAdapter:TransactionAdapter ?=null
    var layoutManager:RecyclerView.LayoutManager?=null
    var empty:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transaction_history_activity)
        recyclerView=findViewById(R.id.recycler_history)
        recyclerView.setHasFixedSize(true)
        layoutManager=LinearLayoutManager(this)
        empty=findViewById(R.id.empty_text)
        recyclerView.layoutManager=layoutManager
        recyclerView.scheduleLayoutAnimation()

    }
    private fun showData(){
        historyList.clear()
        val cursor=BankDbHelper(this).readTransfer()
        while (cursor!!.moveToNext()){
            val balanceAmt=cursor.getInt(3)
            val model:Data= Data(cursor.getString(1),cursor.getString(2),balanceAmt,cursor.getString(4))
            historyList.add(model)
        }
        historyAdapter=TransactionAdapter(this,historyList)
        recyclerView.adapter=historyAdapter

        if (historyList.size==0){
            empty!!.visibility=View.VISIBLE
        }
    }
}