package id.plantaniteam.plantaniapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import id.plantaniteam.plantaniapp.data.model.User
import id.plantaniteam.plantaniapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.icCamera.setOnClickListener {
            Intent(this@MainActivity, CameraActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.icTask.setOnClickListener {
            comingSoon()
        }

        binding.icNotifications.setOnClickListener {
            comingSoon()
        }

        binding.icAccount.setOnClickListener {
            Intent(this@MainActivity, AccountActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun comingSoon() {
        Toast.makeText(applicationContext, "Coming soon!", Toast.LENGTH_SHORT).show()
    }
}