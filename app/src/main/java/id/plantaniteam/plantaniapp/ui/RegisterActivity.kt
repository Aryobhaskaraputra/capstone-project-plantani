package id.plantaniteam.plantaniapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import id.plantaniteam.plantaniapp.R
import id.plantaniteam.plantaniapp.data.model.Result
import id.plantaniteam.plantaniapp.data.model.User
import id.plantaniteam.plantaniapp.databinding.ActivityRegisterBinding
import id.plantaniteam.plantaniapp.session.SessionManager
import id.plantaniteam.plantaniapp.ui.viewmodel.UserViewModel

class RegisterActivity : AppCompatActivity() {
    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    private val viewModel: UserViewModel by viewModels { UserViewModel.Factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        supportActionBar?.hide()

        binding.tvLogin.setOnClickListener {
            finish()
        }

        val arrayProvinces = resources.getStringArray(R.array.provinsi)
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            arrayProvinces
        )
        binding.edtLocation.threshold = 1
        binding.edtLocation.dropDownHeight = ViewGroup.LayoutParams.MATCH_PARENT
        binding.edtLocation.setAdapter(adapter)

        viewModel.result.observe(this) { result ->
            when(result) {
                is Result.Success -> {
                    Toast.makeText(
                        applicationContext,
                        "Berhasil Daftar",
                        Toast.LENGTH_LONG
                    ).show()
                    val user = result.data.data
                    val session = SessionManager(this)
                    session.setUserSession(user)

                    startActivity(
                        Intent(this@RegisterActivity, ViewAllPlantsActivity::class.java)
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

        binding.btnRegister.setOnClickListener {
            doRegister()
        }
    }

    private fun doRegister() {
        var user: User
        binding.apply {
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val username = edtUsername.text.toString()
            val password = edtPassword.text.toString()
            val phoneNumber = edtPhone.text.toString()
            val location = edtLocation.text.toString()

            when {
                name.isEmpty() -> edtName.error = "Masukkan nama anda"
                email.isEmpty() -> edtEmail.error = "Masukkan email anda!"
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> edtEmail.error = "Masukkan email yang valid!"
                username.isEmpty() -> edtUsername.error = "Masukkan username anda!"
                password.isEmpty() -> edtPassword.error = "Masukkan password anda!"

                phoneNumber.isEmpty() -> edtPhone.error = "Masukkan no HP anda"
                location.isEmpty() -> edtLocation.error = "Masukkan Lokasi"
            }

            user = User("",
                name = name,
                username = username,
                email = email,
                password = password,
                phoneNumber = phoneNumber,
                location = convertToId(location)
            )
            viewModel.register(user)
        }
    }

    private fun convertToId(province: String): Int {
        val id: Map<String, Int> = mapOf(
            "Nanggroe Aceh Darussalam" to 1,
            "Sumatera Utara" to 2,
            "Sumatera Selatan" to 3,
            "Sumatera Barat" to 4,
            "Bengkulu" to 5,
            "Riau" to 6,
            "Kepulauan Riau" to 7,
            "Jambi" to 8,
            "Lampung" to 9,
            "Bangka Belitung" to 10,
            "Kalimantan Barat" to 11,
            "Kalimantan Timur" to 12,
            "Kalimantan Selatan" to 13,
            "Kalimantan Tengah" to 14,
            "Kalimantan Utara" to 15,
            "Banten" to 16,
            "Jakarta" to 17,
            "Jawa Barat" to 18,
            "Jawa Tengah" to 19,
            "Daerah Istimewa Yogyakarta" to 20,
            "Jawa Timur" to 21,
            "Bali" to 22,
            "Nusa Tenggara Barat" to 23,
            "Nusa Tenggara Timur" to 24,
            "Gorontalo" to 25,
            "Sulawesi Barat" to 26,
            "Sulawesi Tengah" to 27,
            "Sulawesi Utara" to 28,
            "Sulawesi Tenggara" to 29,
            "Sulawesi Selatan" to 30,
            "Maluku Utara" to 31,
            "Maluku" to 32,
            "Papua Barat" to 33,
            "Papua" to 34,
            "Papua Tengah" to 35,
            "Papua Pegunungan" to 36,
            "Papua Selatan" to 37,
            "Papua Barat Daya" to 38,
            "" to 0,
            "-" to 0,
        )

        return id[province]!!
    }
}