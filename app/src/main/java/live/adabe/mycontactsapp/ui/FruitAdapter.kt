package live.adabe.mycontactsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import live.adabe.mycontactsapp.databinding.FruitItemBinding
import live.adabe.mycontactsapp.model.Fruit

class FruitAdapter(var fruits: List<Fruit>, private val listener: OnFruitItemClickListener) :
    RecyclerView.Adapter<FruitViewHolder>() {

    interface OnFruitItemClickListener {
        fun onItemClick(fruit: Fruit)
        fun onItemDelete(fruit: Fruit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val binding = FruitItemBinding.inflate(LayoutInflater.from(parent.context))
        return FruitViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        holder.bind(fruits[position])
    }

    override fun getItemCount(): Int = fruits.size
}