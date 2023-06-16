package id.plantaniteam.plantaniapp.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class Plants(
	@field:SerializedName("plant_id")
	val plantId: Int,

	@field:SerializedName("plantName")
	val plantName: String,

	@field:SerializedName("latinPlantName")
	val latinPlantName: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("climate")
	val climate: String,

	@field:SerializedName("temperature")
	val temperature: String,

	@field:SerializedName("pH")
	val pH: String,

	@field:SerializedName("treatment")
	val treatment: String,

	@field:SerializedName("category_id")
	val categoryId: Int,

	@field:SerializedName("imageUrl")
	val imageUrl: String,



) : Parcelable
