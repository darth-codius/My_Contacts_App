package live.adabe.mycontactsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fruits")
data class Fruit(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val name : String,
    val color: String,
    @ColumnInfo(name = "is_favourite")
    val isFavourite: Boolean,
)
