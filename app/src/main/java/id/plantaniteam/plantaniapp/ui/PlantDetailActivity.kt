package id.plantaniteam.plantaniapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import id.plantaniteam.plantaniapp.databinding.ActivityPlantDetailBinding
import id.plantaniteam.plantaniapp.ui.viewmodel.PlantsViewModel
import id.plantaniteam.plantaniapp.data.model.Result

class PlantDetailActivity : AppCompatActivity() {
    private val binding: ActivityPlantDetailBinding by lazy {
        ActivityPlantDetailBinding.inflate(layoutInflater)
    }
    private val viewModel: PlantsViewModel by viewModels { PlantsViewModel.Factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        val id = intent.getIntExtra("plants_id", 0)

        Log.d("TAG", id.toString())

       viewModel.getPlantsById(id)

        viewModel.resultPlant.observe(this) { plants ->
            if (plants != null) {
                if (plants is Result.Success) {
                    val plant = plants.data
                    binding.tvDetails.text = plant.description
                    binding.tvTreatment.text = plant.treatment
                    binding.tvClimate.text = plant.climate

                    Glide.with(this)
                        .load(plant.imageUrl)
                        .fitCenter()
                        .into(binding.ivPlants)
                }
            }
        }
    }
}