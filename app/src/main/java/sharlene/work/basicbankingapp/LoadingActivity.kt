package sharlene.work.basicbankingapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class LoadingActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.success_activity)
        val handler=Handler()
        handler.postDelayed({
            val intent = Intent(this,TransactionSuccessActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }

    companion object{
        private const val SPLASH_TIME_OUT=4000
    }
}