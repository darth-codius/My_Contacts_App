package live.adabe.mycontactsapp.ui

import androidx.recyclerview.widget.RecyclerView
import live.adabe.mycontactsapp.databinding.FruitItemBinding
import live.adabe.mycontactsapp.model.Fruit

class FruitViewHolder(private val binding: FruitItemBinding, val listener: FruitAdapter.OnFruitItemClickListener) :
    RecyclerView.ViewHolder(binding.root) {

        fun bind(fruit: Fruit){
            binding.apply {
                root.setOnClickListener{
                    listener.onItemClick(fruit)
                }
                deleteFruitBtn.setOnClickListener{
                    listener.onItemDelete(fruit)
                }
                fruitNameTv.text = fruit.name
            }
        }
}