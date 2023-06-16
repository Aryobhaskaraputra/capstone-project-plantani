package id.plantaniteam.plantaniapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.plantaniteam.plantaniapp.databinding.ActivityAccountBinding

class AccountActivity : AppCompatActivity() {
    private val binding : ActivityAccountBinding by lazy {
        ActivityAccountBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        with(binding) {
            icBack.setOnClickListener {
                finish()
            }
        }
    }
}