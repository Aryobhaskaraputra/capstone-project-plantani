package id.plantaniteam.plantaniapp.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PredictionResponse(
    @SerializedName("penyakit")
    val penyakit: String,
    @SerializedName("tanaman")
    val tanaman: String,
): Parcelable
