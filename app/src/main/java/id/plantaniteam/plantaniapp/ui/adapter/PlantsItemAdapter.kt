package id.plantaniteam.plantaniapp.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.plantaniteam.plantaniapp.data.model.Plants
import id.plantaniteam.plantaniapp.databinding.ItemPlantsBinding
import id.plantaniteam.plantaniapp.ui.PlantDetailActivity

class PlantsItemAdapter(private val listPlants: List<Plants>) : RecyclerView.Adapter<PlantsItemAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemPlantsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(plants: Plants) {
            binding.tvPlantsName.text = plants.plantName
            Glide.with(itemView.context)
                .load(plants.imageUrl)
                .into(binding.ivPlantsImage)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, PlantDetailActivity::class.java)
                intent.putExtra("plants_id", plants.plantId)

                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPlantsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listPlants[position])
    }

    override fun getItemCount(): Int = listPlants.size
}