package id.plantaniteam.plantaniapp.data.repository

import id.plantaniteam.plantaniapp.data.model.User
import id.plantaniteam.plantaniapp.data.source.remote.PlantaniApiService
import id.plantaniteam.plantaniapp.data.source.remote.response.AuthenticationResponse
import retrofit2.Response

class UserRepository constructor(private val apiService: PlantaniApiService) {
    suspend fun login(user: User) : Response<AuthenticationResponse> = apiService.login(user)

    suspend fun register(user: User) : Response<AuthenticationResponse> = apiService.register(user)

    suspend fun getUser(id: String) : Response<AuthenticationResponse> = apiService.getUsers(id)
}