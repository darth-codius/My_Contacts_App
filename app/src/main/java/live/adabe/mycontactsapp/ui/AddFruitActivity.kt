package live.adabe.mycontactsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import live.adabe.mycontactsapp.databinding.ActivityAddFruitBinding
import live.adabe.mycontactsapp.db.FruitDatabase
import live.adabe.mycontactsapp.model.Fruit

class AddFruitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddFruitBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var database: FruitDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFruitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FruitDatabase.getInstance(this)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.saveButton.setOnClickListener {
            getInput()?.let { it1 -> database.fruitDao().addFruit(it1) }
        }
    }

    private fun isValidInput(): Boolean {
        return binding.fruitNameInput.editText?.text.toString().trim().isNotEmpty() &&
                binding.fruitColorInput.editText?.text.toString().trim().isNotEmpty()
    }

    private fun getInput(): Fruit? {
        if (isValidInput()) {
            return Fruit(
                name = binding.fruitNameInput.editText?.text.toString(),
                color = binding.fruitColorInput.editText?.text.toString(),
                isFavourite = binding.isFavouriteCb.isChecked,
                uid = 0
            )
        }
        return null
    }
}