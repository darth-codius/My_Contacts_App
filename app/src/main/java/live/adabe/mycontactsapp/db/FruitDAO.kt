package live.adabe.mycontactsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import live.adabe.mycontactsapp.model.Fruit

@Dao
interface FruitDAO {
    @Query("SELECT * FROM fruits")
    fun getAllFruits(): List<Fruit>

    @Insert
    fun addFruit(fruit: Fruit)

    @Delete
    fun deleteFruit(fruit: Fruit)
}