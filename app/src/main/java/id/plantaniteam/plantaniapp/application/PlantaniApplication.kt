package id.plantaniteam.plantaniapp.application

import android.app.Application
import id.plantaniteam.plantaniapp.BuildConfig
import id.plantaniteam.plantaniapp.data.repository.CameraRepository
import id.plantaniteam.plantaniapp.data.repository.PlantsRepository
import id.plantaniteam.plantaniapp.data.repository.UserRepository
import id.plantaniteam.plantaniapp.data.source.remote.PlantaniApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class PlantaniApplication : Application() {
    /**
     * Provide Retrofit
     */
    private val retrofit: Retrofit by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            )

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()


        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private val retrofitForDB: Retrofit by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            )

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()


        Retrofit.Builder()
            .baseUrl(BASE_DB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private val apiService: PlantaniApiService = retrofit.create(PlantaniApiService::class.java)
    private val apiDbService: PlantaniApiService = retrofitForDB.create(PlantaniApiService::class.java)

    val userRepository: UserRepository = UserRepository(apiDbService)
    val cameraRepository: CameraRepository = CameraRepository(apiService)
    val plantsRepository: PlantsRepository = PlantsRepository(apiDbService)

    companion object {
        private const val BASE_URL: String = "https://api-xbag27sv3a-et.a.run.app/"
        private const val BASE_DB_URL: String = "https://plantani-app.et.r.appspot.com/"
    }
}