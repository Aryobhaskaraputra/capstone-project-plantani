package id.plantaniteam.plantaniapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.plantaniteam.plantaniapp.data.model.Result
import id.plantaniteam.plantaniapp.application.PlantaniApplication
import id.plantaniteam.plantaniapp.data.model.User
import id.plantaniteam.plantaniapp.data.repository.UserRepository
import id.plantaniteam.plantaniapp.data.source.remote.response.AuthenticationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _result = MutableLiveData<Result<AuthenticationResponse>>()
    val result: LiveData<Result<AuthenticationResponse>> = _result

    fun login(user: User) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.login(user)
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data?.error != null) {
                        _result.postValue(Result.Error(data.error!!))
                    } else {
                        _result.postValue(Result.Success(data!!))
                    }
                }
            }
        }
    }

    fun register(user: User) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.register(user)
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data?.error != null) {
                        _result.postValue(Result.Error(data.error!!))
                    }

                    _result.postValue(Result.Success(data!!))
                } else {
                    _result.postValue(Result.Error(response.message()))
                }
            }
        }
    }

    fun getUser(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.getUser(id)
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data?.error != null) {
                        _result.postValue(Result.Error(data.error!!))
                    }
                    _result.postValue(Result.Success(data!!))
                } else {
                    _result.postValue(Result.Error(response.message()))
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val userRepository: UserRepository = (this[APPLICATION_KEY] as PlantaniApplication).userRepository
                UserViewModel(
                    userRepository
                )
            }
        }
    }
}