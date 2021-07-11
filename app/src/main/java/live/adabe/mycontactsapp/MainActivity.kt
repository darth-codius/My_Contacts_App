package live.adabe.mycontactsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import live.adabe.mycontactsapp.databinding.ActivityMainBinding
import live.adabe.mycontactsapp.db.FruitDatabase
import live.adabe.mycontactsapp.model.Fruit
import live.adabe.mycontactsapp.ui.AddFruitActivity
import live.adabe.mycontactsapp.ui.FruitAdapter
import live.adabe.mycontactsapp.ui.FruitDetailsActivity
import live.adabe.mycontactsapp.ui.MainViewModel

class MainActivity : AppCompatActivity() {

    companion object{
        const val NAME_KEY = "name"
        const val COLOR_KEY = "color"
        const val FAVOURITE_KEY = "favourite"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: FruitDatabase
    private lateinit var viewModel: MainViewModel
    private lateinit var fruitAdapter: FruitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FruitDatabase.getInstance(this)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getFruit(database)
        fruitAdapter = FruitAdapter(arrayListOf(), listener)
        binding.fruitsRv.apply {
            adapter = fruitAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        binding.addContactBtn.setOnClickListener{
            with(Intent(this, AddFruitActivity::class.java)){
                startActivity(this)
            }
        }

        viewModel.fruitsLiveData.observe(this, {fruits ->
            fruitAdapter.fruits = fruits
            fruitAdapter.notifyDataSetChanged()
        })
    }


    private val listener = object: FruitAdapter.OnFruitItemClickListener{
        override fun onItemClick(fruit: Fruit) {
            with(Intent(this@MainActivity, FruitDetailsActivity::class.java)){
                putExtra(NAME_KEY, fruit.name)
                putExtra(COLOR_KEY, fruit.color)
                putExtra(FAVOURITE_KEY, fruit.isFavourite)
            }
        }

        override fun onItemDelete(fruit: Fruit) {
            database.fruitDao().deleteFruit(fruit)
        }

    }

}