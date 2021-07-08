package sharlene.work.basicbankingapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TransactionSuccessActivity:AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if (id==R.id.action_history){
            val intent=Intent(this,TransactionActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transaction_success_activity)
        val button:Button=findViewById(R.id.back_to_home)
        button.setOnClickListener{
            val intent=Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}