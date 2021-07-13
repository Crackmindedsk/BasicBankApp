package sharlene.work.basicbankingapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    val context: Context = this
    var Name: String? = "Not Exist"
    var Email: String? = "Not Exist"
    var Account_Number: String? = "Not Exist"
    var Phone_Number: String? = "Not Exist"
    var Balance = 0

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
        setContentView(R.layout.profile_details)
        val extras: Bundle? = intent.extras

        if (extras != null) {
            Name = extras.getString("name")
            Email = extras.getString("email")
            Balance = extras.getInt("balance", -1)
            Account_Number = extras.getString("account")
            Phone_Number = extras.getString("phone")

            val text_name: TextView = findViewById<View>(R.id.name_text) as TextView
            text_name.text = Name

            val text_email: TextView = findViewById<View>(R.id.email_text) as TextView
            text_email.text = Email

            val text_balance: TextView = findViewById<View>(R.id.bank_balance_text) as TextView
            text_balance.text = Balance.toString()

            val text_account: TextView = findViewById<View>(R.id.bank_account) as TextView
            text_account.text = Account_Number

            val text_phone: TextView = findViewById<View>(R.id.phone_number) as TextView
            text_phone.text = Phone_Number
        }
        val transfer_button = findViewById<View>(R.id.transfer_button) as Button
        transfer_button.setOnClickListener {
            enterAmount()
        }
    }

    private fun enterAmount() {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val prompt: View = inflater.inflate(R.layout.prompt, null)
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setView(prompt)
        val userInput: EditText = prompt.findViewById<View>(R.id.dialog_box) as EditText
        alertDialogBuilder
            .setCancelable(false)
            .setPositiveButton("SEND") { _, _ ->
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
                cancelTransaction()
            }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val InputNumber = userInput.text.toString()
            var finalValue = 0
            try {
                finalValue = InputNumber.toInt()
            } catch (nfe: NumberFormatException) {
                nfe.printStackTrace()
            }
            if (InputNumber.length > 9) finalValue = Int.MAX_VALUE
            if (TextUtils.isEmpty(InputNumber)) {
                val toast =
                    Toast.makeText(applicationContext, "Amount can't be empty", Toast.LENGTH_SHORT)
                toast.show()
            } else if (finalValue > Balance) {
                val toast = Toast.makeText(
                    applicationContext,
                    "Your Account don't have sufficient balance",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            } else {
                val toast = Toast.makeText(applicationContext, "Proceeding", Toast.LENGTH_LONG)
                toast.show()
                val intent = Intent(this,RecieverActivity::class.java)
//                intent.putExtra("amount", finalValue)
//                intent.putExtra("name", Name)
//                intent.putExtra("email", Email)
//                intent.putExtra("balance", Balance)
                startActivity(intent)
                finish()
            }
        }

    }

    private fun cancelTransaction() {
        val exit_button = AlertDialog.Builder(this@ProfileActivity)
        exit_button.setTitle("Do you want to cancel the transcation?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialogInterface, i ->
                BankDbHelper(this@ProfileActivity).insertTransferData(
                    Name,
                    "Not selected",
                    0,
                    "FAILED"
                )
                Toast.makeText(this@ProfileActivity, "Transaction Cancelled", Toast.LENGTH_LONG)
                    .show()
            }.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
                enterAmount()
            }
        val exitAlert = exit_button.create()
        exitAlert.show()
    }

}