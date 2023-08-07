package uz.uzgidro.ugenews.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsModel(
    val id: Int? = null,
    val title: String? = null,
    val smallText: String? = null,
    val text: String? = null,
    val date: String? = null,
    val img: String? = null,
    val views: Int? = null
): Parcelable
