package study.android.kotlindbnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_stat.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.android.awaitFrame

class StatActivity : AppCompatActivity() {
    val db by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "results.db"
        ).build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stat)
        db.resultsDao().getSum().observe(this, {
            if(it != null){
                money.text = it.toString()
            }
            else{
                money.text = "Данные удалены"
            }
        })
        db.resultsDao().getAvg().observe(this, {
            if(it != null){
                good.text = it.toString()
            }
            else{
                good.text = "0"
            }
        })
        db.resultsDao().getEng().observe(this, {
            if(it != null){
                english.text = it.toString()
            }
            else{
                english.text = "0"
            }
        })
        db.resultsDao().getMax().observe(this, {
            if(it != null){
                best.text = it.toString()
            }
            else{
                best.text = "Данные удалены"
            }
        })
        db.resultsDao().getLongest().observe(this, {
            if(it != null){
                longest.text = it.toString()
            }
            else{
                longest.text = "Данные удалены"
            }
        })
    }
}