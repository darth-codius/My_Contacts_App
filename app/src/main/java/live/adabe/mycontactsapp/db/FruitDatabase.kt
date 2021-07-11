package live.adabe.mycontactsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import live.adabe.mycontactsapp.model.Fruit


@Database(entities = [Fruit::class], version = 1)
abstract class FruitDatabase : RoomDatabase() {
    abstract fun fruitDao(): FruitDAO

    companion object {
        private var INSTANCE: FruitDatabase? = null

        fun getInstance(context: Context): FruitDatabase {
            INSTANCE?.let {
                return it
            } ?: kotlin.run {
                INSTANCE =
                    Room.databaseBuilder(context, FruitDatabase::class.java, "fruit-database")
                        .allowMainThreadQueries().build()
                return INSTANCE!!
            }
        }
    }
}