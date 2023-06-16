package id.plantaniteam.plantaniapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import id.plantaniteam.plantaniapp.databinding.ActivityViewAllPlantsBinding
import id.plantaniteam.plantaniapp.ui.viewmodel.PlantsViewModel
import id.plantaniteam.plantaniapp.data.model.Result
import id.plantaniteam.plantaniapp.ui.adapter.PlantsItemAdapter

class ViewAllPlantsActivity : AppCompatActivity() {
    private val binding: ActivityViewAllPlantsBinding by lazy {
        ActivityViewAllPlantsBinding.inflate(layoutInflater)
    }
    private val viewModel: PlantsViewModel by viewModels { PlantsViewModel.Factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.rvPlants.layoutManager = GridLayoutManager(this, 2)
        binding.rvPlants.setHasFixedSize(true)

        viewModel.getPlants()
        viewModel.result.observe(this) { plants ->
            if (plants != null) {
                if (plants is Result.Success) {
                    val adapter = PlantsItemAdapter(plants.data)
                    binding.rvPlants.adapter = adapter
                }
            }
        }

        binding.icCamera.setOnClickListener {
            Intent(this@ViewAllPlantsActivity, CameraActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}