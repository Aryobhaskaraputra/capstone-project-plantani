package id.plantaniteam.plantaniapp.ui.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.plantaniteam.plantaniapp.application.PlantaniApplication
import id.plantaniteam.plantaniapp.data.model.Result
import id.plantaniteam.plantaniapp.data.repository.CameraRepository
import id.plantaniteam.plantaniapp.data.source.remote.response.PredictionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

class CameraViewModel constructor(
    private val repository: CameraRepository
): ViewModel() {
    private var _result = MutableLiveData<Result<PredictionResponse>>()
    val result: LiveData<Result<PredictionResponse>>  = _result

    fun predictImage(image: MultipartBody.Part) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = repository.predictImage(image)
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        _result.postValue(Result.Success(responseData!!))
                    } else {
                        _result.postValue(Result.Error(response.message()))
                    }
                } catch (e: Exception) {
                    _result.postValue(Result.Error(e.message.toString()))
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val cameraRepository: CameraRepository = (this[APPLICATION_KEY] as PlantaniApplication).cameraRepository
                CameraViewModel(
                    cameraRepository
                )
            }
        }
    }
}