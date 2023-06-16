package id.plantaniteam.plantaniapp.data.repository

import id.plantaniteam.plantaniapp.data.source.remote.PlantaniApiService
import okhttp3.MultipartBody

class CameraRepository(private val apiService: PlantaniApiService) {
    suspend fun predictImage(image: MultipartBody.Part) = apiService.predictImage(image)
}