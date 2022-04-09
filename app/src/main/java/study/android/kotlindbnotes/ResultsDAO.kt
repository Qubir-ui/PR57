package study.android.kotlindbnotes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ResultsDao {
    @Query("SELECT * FROM results ORDER BY :order")
    fun getAll(order: String): LiveData<List<ResultEntity>>
    @Query("SELECT SUM(result) FROM results")
    fun getSum(): LiveData<Int>
    @Query("SELECT COUNT(*) FROM results CROSS JOIN (SELECT AVG(result) avg_ID FROM results) avg_ID WHERE result > avg_ID.avg_ID")
    fun getAvg(): LiveData<Int>
    @Query("SELECT COUNT(*) FROM results WHERE name < 'А'")
    fun getEng(): LiveData<Int>
    @Query("SELECT name FROM results CROSS JOIN (SELECT MAX(result) avg_ID FROM results) avg_ID WHERE result = avg_ID.avg_ID ORDER BY name LIMIT 1")
    fun getMax(): LiveData<String>
    @Query("SELECT name FROM results ORDER BY LENGTH(name) DESC, Name LIMIT 1")
    fun getLongest(): LiveData<String>
    @Query("DELETE FROM results WHERE name LIKE :name")
    fun deleteByName(name: String)
    @Insert
    fun insert(vararg result: ResultEntity)
    @Delete
    fun delete(result: ResultEntity)
    @Update
    fun update(vararg result: ResultEntity)
}