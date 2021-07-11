package live.adabe.mycontactsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import live.adabe.mycontactsapp.db.FruitDatabase
import live.adabe.mycontactsapp.model.Fruit

class MainViewModel : ViewModel() {
    val fruitsLiveData = MutableLiveData<List<Fruit>>()
    
    fun getFruit(database: FruitDatabase){
        fruitsLiveData.postValue(database.fruitDao().getAllFruits())
    }
    
    fun saveFruit(database: FruitDatabase, fruit: Fruit){
        database.fruitDao().addFruit(fruit)
        getFruit(database)
    }
}