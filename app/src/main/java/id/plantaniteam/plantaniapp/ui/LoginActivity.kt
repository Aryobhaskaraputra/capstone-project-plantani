package id.plantaniteam.plantaniapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import id.plantaniteam.plantaniapp.data.model.User
import id.plantaniteam.plantaniapp.data.model.Result
import id.plantaniteam.plantaniapp.databinding.ActivityLoginBinding
import id.plantaniteam.plantaniapp.session.SessionManager
import id.plantaniteam.plantaniapp.ui.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val viewModel: UserViewModel by viewModels { UserViewModel.Factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnLogin.setOnClickListener { doLogin() }

        binding.tvSignUp.setOnClickListener {
            Intent(this@LoginActivity, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }

        viewModel.result.observe(this) { result ->
            if (result != null) {
                when(result) {
                    is Result.Success -> {
                        val user = result.data.data
                        val session = SessionManager(this)
                        session.setUserSession(user)

                        startActivity(
                            Intent(this@LoginActivity, ViewAllPlantsActivity::class.java)
                        )
                        finish()
                    }
                    is Result.Error -> {
                        Toast.makeText(this, result.error, Toast.LENGTH_LONG).show()
                    }
                    is Result.Loading -> {

                    }
                }
            }
        }
    }

    private fun doLogin() {
        // Validation
        binding.apply {
            val user: User
            val username: String = edtUsername.text.toString()
            val password: String = edtPassword.text.toString()

            when {
                username.isEmpty() -> edtUsername.error = "Masukkan username anda"
                password.isEmpty() -> edtPassword.error = "Masukkan kata sandi anda"
            }

            user = User(
                "",
                "",
                username = username,
                "",
                password = password,
                "",
                0,
            )

            viewModel.login(user)
        }
    }
}