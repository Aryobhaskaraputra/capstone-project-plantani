package id.plantaniteam.plantaniapp.ui.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.plantaniteam.plantaniapp.application.PlantaniApplication
import id.plantaniteam.plantaniapp.data.model.Plants
import id.plantaniteam.plantaniapp.data.repository.PlantsRepository
import id.plantaniteam.plantaniapp.data.model.Result
import id.plantaniteam.plantaniapp.data.source.remote.response.PlantsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlantsViewModel(
    private val repository: PlantsRepository
): ViewModel() {
    private var _result = MutableLiveData<Result<List<Plants>>>()
    val result: LiveData<Result<List<Plants>>> = _result

    private var _resultPlant = MutableLiveData<Result<Plants>>()
    val resultPlant: LiveData<Result<Plants>> = _resultPlant

    fun getPlants() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.getPlants()
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        _result.postValue(Result.Success(data.plants))
                    }
                }
            }
        }
    }

    fun getPlantsById(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.getPlantsById(id)
                if (response.isSuccessful) {
                    val data = response.body()!!
                    _resultPlant.postValue(Result.Success(data.plant))
                }
            }
        }
    }

//    fun get(): LiveData<Result<Plants>> {
//
//    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val plantsRepository: PlantsRepository = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PlantaniApplication).plantsRepository
                PlantsViewModel(
                    plantsRepository
                )
            }
        }
    }
}