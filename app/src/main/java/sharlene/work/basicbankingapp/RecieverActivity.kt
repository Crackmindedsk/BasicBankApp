package sharlene.work.basicbankingapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sharlene.work.basicbankingapp.model.Data
import sharlene.work.basicbankingapp.model.RecieverAdapter

class RecieverActivity : AppCompatActivity() {
    var dbHelper: BankDbHelper? = null
    var recieverList: MutableList<Data> = ArrayList()
    var Name: String? = null
    var Email: String? = null
    var recieverEmail: String? = null
    var recieverName: String? = null
    var recieverBalance: Int? = null
    var balance: Int? = null
    var transferAmt: Int? = null
    var remainingAmt: Int? = null
    var adapter: RecieverAdapter? = null
    var viewList: RecyclerView? = null
    val layoutManager1: RecyclerView.LayoutManager = LinearLayoutManager(this)
    private val listener: RecieverAdapter.RecyclerviewClickListener? = null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_history) {
            val intent = Intent(this, TransactionActivity::class.java)
            startActivity(intent)
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reciever_activity)
        viewList = findViewById<View>(R.id.reciever_list_view) as RecyclerView
        viewList!!.layoutManager = layoutManager1
        recieverList=ArrayList()
        //animation
        dbHelper = BankDbHelper(this)
        val bundle:Bundle? = intent.extras
        if (bundle != null) {
            Name = bundle.getString("name")
            Email = bundle.getString("email")
            transferAmt = bundle.getInt("amount")
            balance = bundle.getInt("balance")
            showData(Email)
        }

    }

    private fun showData(Email: String?) {
        recieverList.clear()
        val cursor = dbHelper?.readSelect(Email!!)
        cursor?.moveToFirst()
        do {
            val name = cursor?.getString(1)
            val balance = cursor?.getInt(3)
            val email = cursor?.getString(2)
            val model = Data(cursor?.getString(1), cursor?.getString(2), balance!!)
            recieverList.add(model)
        } while (cursor!!.moveToNext())
        adapter = RecieverAdapter(this, recieverList, listener)
        viewList!!.adapter = adapter
    }

    fun selectUser(position: Int) {
        recieverEmail = recieverList[position].email
        println(recieverEmail)
        val cursor = BankDbHelper(this).readAData(recieverEmail!!)
        while (cursor!!.moveToNext()) {
            recieverBalance = cursor.getInt(3)
            recieverName = cursor.getString(1)
            val finalAmount = recieverBalance!! + transferAmt!!
            BankDbHelper(this).update(Email!!, finalAmount.toString())
            remainingAmt = balance!! - transferAmt!!
            BankDbHelper(this).update(Email!!, remainingAmt.toString())
            BankDbHelper(this).insertTransferData(Name, recieverName!!, transferAmt!!, "SUCCESSFUL")
            Toast.makeText(this, "Transaction Successful", Toast.LENGTH_LONG).show()
            //successfull splash
            startActivity(Intent(this,TransactionSuccessActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        val exitButton = AlertDialog.Builder(this)
        exitButton.setTitle("Do you want to cancel the transaction?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                BankDbHelper(this).insertTransferData(Name, "Not selected", transferAmt!!, "FAILED")
                Toast.makeText(this, "Transaction Cancelled!", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, ViewListActivity::class.java))
                finish()
            }.setNegativeButton("No", null)
        val alert = exitButton.create()
        alert.show()
    }

}


