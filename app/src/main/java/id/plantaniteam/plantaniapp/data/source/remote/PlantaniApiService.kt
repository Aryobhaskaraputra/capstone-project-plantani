package id.plantaniteam.plantaniapp.data.source.remote

import id.plantaniteam.plantaniapp.data.model.User
import id.plantaniteam.plantaniapp.data.source.remote.response.AuthenticationResponse
import id.plantaniteam.plantaniapp.data.source.remote.response.PlantResponse
import id.plantaniteam.plantaniapp.data.source.remote.response.PlantsResponse
import id.plantaniteam.plantaniapp.data.source.remote.response.PredictionResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface PlantaniApiService {
    @POST("login")
    suspend fun login(
        @Body user: User
    ): Response<AuthenticationResponse>

    @POST("register")
    suspend fun register(
        @Body user: User
    ): Response<AuthenticationResponse>

    @GET("users/{id}")
    suspend fun getUsers(
        @Path("id") id: String
    ): Response<AuthenticationResponse>

    @GET("plants")
    suspend fun getPlants(

    ): Response<PlantsResponse>

    @GET("plants/{id}")
    suspend fun getPlantById(
        @Path("id") id: Int
    ): Response<PlantResponse>

    @GET("plants-disease")
    suspend fun getPlantsDisease(

    ): Response<PlantsResponse>

    @Multipart
    @POST("predict")
    suspend fun predictImage(
        @Part file: MultipartBody.Part
    ): Response<PredictionResponse>
}