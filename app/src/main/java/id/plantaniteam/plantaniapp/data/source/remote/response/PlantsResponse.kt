package id.plantaniteam.plantaniapp.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import id.plantaniteam.plantaniapp.data.model.Plants
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlantsResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("plants")
    val plants: List<Plants>
): Parcelable

data class PlantResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("plant")
    val plant: Plants
)