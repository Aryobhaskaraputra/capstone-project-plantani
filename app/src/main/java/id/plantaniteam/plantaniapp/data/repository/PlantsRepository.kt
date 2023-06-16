package id.plantaniteam.plantaniapp.data.repository

import id.plantaniteam.plantaniapp.data.source.remote.PlantaniApiService
import id.plantaniteam.plantaniapp.data.source.remote.response.PlantResponse
import id.plantaniteam.plantaniapp.data.source.remote.response.PlantsResponse
import retrofit2.Response

class PlantsRepository constructor(private val apiService: PlantaniApiService) {
    suspend fun getPlants(): Response<PlantsResponse> = apiService.getPlants()

    suspend fun getPlantsById(id: Int): Response<PlantResponse> = apiService.getPlantById(id)

    suspend fun getPlantsDisease(): Response<PlantsResponse> = apiService.getPlantsDisease()

    suspend fun getPlantsDiseaseById(id: Int) = null
}