package sharlene.work.basicbankingapp

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity:AppCompatActivity() {
    val context:Context=this
    var Name="Not Exist"
    var Email="not Exist"
    var Account_Number="Not Exist"
    var Phone_Number="Not Exist"

    var Balance=0

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
        setContentView(R.layout.profile_details)
        val extras: Bundle? =intent.extras

        if(extras!=null){
            Name= extras.getString("name").toString()
            Email=extras.getString("email").toString()
            Balance=extras.getInt("balance",-1)
            Account_Number=extras.getString("account").toString()
            Phone_Number=extras.getString("phone").toString()

            val text_name:TextView=findViewById(R.id.name_text)
            text_name.setText(Name)

            val text_email:TextView=findViewById(R.id.email_text)
            text_email.setText(Email)

            val text_balance:TextView=findViewById(R.id.bank_balance_text)
            text_balance.setText(Balance)

            val text_account:TextView=findViewById(R.id.bank_account)
            text_account.setText(Account_Number)

            val text_phone:TextView=findViewById(R.id.phone_number)
            text_phone.setText(Phone_Number)
        }
        val transfer_button:Button=findViewById(R.id.transfer_button)
        transfer_button.setOnClickListener {
            enterAmount()
        }
    }
    private fun enterAmount(){
        val inflater:LayoutInflater= LayoutInflater.from(context)
        val prompt:View=inflater.inflate(R.layout.prompt,null)
        val alertDialogBuilder=AlertDialog.Builder(context)
        alertDialogBuilder.setView(prompt)
        val userInput:EditText=prompt.findViewById(R.id.dialog_box)
        alertDialogBuilder
            .setCancelable(false)
            .setPositiveButton("SEND", DialogInterface.OnClickListener(DialogInterface,Int))
            .setNegativeButton("Cancel",)

        
    }

}