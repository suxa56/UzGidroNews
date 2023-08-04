package uz.uzgidro.ugenews.domain

data class NewsModel(
    val id: Int? = null,
    val title: String? = null,
    val smallText: String? = null,
    val text: String? = null,
    val date: String? = null,
    val img: String? = null,
    val views: Int? = null
)
