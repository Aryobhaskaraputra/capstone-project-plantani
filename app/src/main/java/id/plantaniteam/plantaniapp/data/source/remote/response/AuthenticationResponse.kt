package id.plantaniteam.plantaniapp.data.source.remote.response

import com.google.gson.annotations.SerializedName
import id.plantaniteam.plantaniapp.data.model.User

data class AuthenticationResponse(
    @SerializedName("error")
    var error: String? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("data")
    val data: User
)
