package study.android.kotlindbnotes

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import androidx.lifecycle.Observer as Observer

class MainActivity : AppCompatActivity() {
    val db by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "results.db"
        ).build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var context = this
            GlobalScope.launch {
                db.clearAllTables()
                for(company in TestData.russianCompanies2020) {
                db.resultsDao().insert(company)
            }
        }
        companies_list.layoutManager = LinearLayoutManager(this)
        db.resultsDao().getAll("RESULT DESC").observe(this,
            { results -> companies_list.adapter = ResultAdapter(results) })
        val toDelete = findViewById<EditText>(R.id.toDelete)
        delete.setOnClickListener {
            //db.resultsDao().deleteByName(toDelete.text.toString())
            GlobalScope.launch {
                db.resultsDao().deleteByName("%${toDelete.text}%")
            }
        }
        statistics.setOnClickListener {
            startActivity(Intent(this, StatActivity::class.java))
        }
    }
}