package id.plantaniteam.plantaniapp.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import id.plantaniteam.plantaniapp.R
import id.plantaniteam.plantaniapp.data.model.Result
import id.plantaniteam.plantaniapp.databinding.ActivityCameraBinding
import id.plantaniteam.plantaniapp.helper.Utils.bitmapToFile
import id.plantaniteam.plantaniapp.helper.Utils.reduceFile
import id.plantaniteam.plantaniapp.helper.Utils.timeStamp
import id.plantaniteam.plantaniapp.helper.Utils.toBitmap
import id.plantaniteam.plantaniapp.ui.viewmodel.CameraViewModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity() {
    private val binding: ActivityCameraBinding by lazy {
        ActivityCameraBinding.inflate(layoutInflater)
    }
    private var imageCapture : ImageCapture? = null
    private val viewModel: CameraViewModel by viewModels { CameraViewModel.Factory }

    private lateinit var cameraExecutor: ExecutorService
    private var isCameraFrozen: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        hideSystemUI()

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }

        cameraExecutor = Executors.newSingleThreadExecutor()

        binding.icCaptureCamera.setOnClickListener { takePhoto() }
        binding.icClose.setOnClickListener {
            binding.clOverlay.visibility = View.GONE
        }
        binding.icBack.setOnClickListener { finish() }

        viewModel.result.observe(this@CameraActivity) { prediction ->
            if (prediction != null) {
                when(prediction) {
                    is Result.Success -> {
                        val data = prediction.data
                        binding.clOverlay.visibility = View.VISIBLE
                        binding.tvPredictedPlants.text = data.tanaman
                        binding.tvDisease.text = data.penyakit
                    }

                    is Result.Error -> {
                        Toast.makeText(
                            this@CameraActivity,
                            "Error: ${prediction.error}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is Result.Loading -> {

                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(this@CameraActivity, "Tidak mendapatkan izin!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    /**
     * Fungsi untuk menyembunyikan ActionBar dan StatusBar.
     * Untuk Android 12 ke atas, terdapat cara yang berbeda dalam menghilangkannya.
     */
    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun allPermissionsGranted() =
        REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                baseContext, it
            ) == PackageManager.PERMISSION_GRANTED
        }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this@CameraActivity)

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
            }

            imageCapture = ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this@CameraActivity, cameraSelector, preview, imageCapture
                )
            } catch (e: Exception) {
                Toast.makeText(
                    this@CameraActivity,
                    "Gagal membuka kamera: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        imageCapture.takePicture(
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    Toast.makeText(
                        this@CameraActivity,
                        "Success!",
                        Toast.LENGTH_LONG
                    ).show()
                    val bitmap = toBitmap(image)
                    image.close()
                    val file = File(getOutputDirectory(), "IMG_${timeStamp}.jpg")
                    val fileConverted = bitmapToFile(bitmap, file)

                    uploadImage(fileConverted)
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(
                        this@CameraActivity,
                        "Gagal mengambil gambar!",
                        Toast.LENGTH_LONG
                    ).show()
                    super.onError(exception)
                }
            }
        )
    }

    private fun uploadImage(image: File) {
        val reducedImageFile = reduceFile(image)
        val imagePart = reducedImageFile.asRequestBody("image/jpeg".toMediaType())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "file",
            image.name,
            imagePart
        )

        viewModel.predictImage(imageMultipart)
    }

    private fun getOutputDirectory(): File {
        val mediaDir = application.externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }

        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }

    companion object {
        private const val REQUEST_CODE_PERMISSIONS: Int = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }
}