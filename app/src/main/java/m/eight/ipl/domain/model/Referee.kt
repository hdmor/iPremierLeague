package m.eight.ipl.domain.model

import com.google.gson.annotations.SerializedName

data class Referee(
    val id: Int,
    val name: String,
    val type: String,
    val nationality: String
)
