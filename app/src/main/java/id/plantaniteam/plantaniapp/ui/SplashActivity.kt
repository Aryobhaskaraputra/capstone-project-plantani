package id.plantaniteam.plantaniapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.plantaniteam.plantaniapp.databinding.ActivitySplashBinding
import id.plantaniteam.plantaniapp.helper.Utils
import id.plantaniteam.plantaniapp.session.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    private val activityScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    /**
     * Menampilkan Splash Screen selama 3 detik,
     * lalu melakukan Intent (pindah) ke Activity yang ditentukan oleh kondisi berikut:
     * Jika user sedang melakukan login dan belum melakukan logout, maka akan langsung dipindah ke halaman utama.
     * Namun, jika tidak, maka ke halaman login
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        activityScope.launch {
            delay(Utils.SPLASH_SCREEN_TIME)

            val session = SessionManager(this@SplashActivity).getUserSession()

            Intent(
                this@SplashActivity,
                if (session.isEmpty())
                    LoginActivity::class.java
                else
                    ViewAllPlantsActivity::class.java
            ).also {
                startActivity(it)
                finish()
            }
        }
    }
}