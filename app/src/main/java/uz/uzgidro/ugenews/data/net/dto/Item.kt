package uz.uzgidro.ugenews.data.net.dto

data class Item(
    val id: Int? = null,
    val uz: String? = null,
    val uztext: String? = null,
    val ru: String? = null,
    val rutext: String? = null,
    val eng: String? = null,
    val engtext: String? = null,
    val date: String? = null,
    val img: String? = null,
    val uzsmall: String? = null,
    val rusmall: String? = null,
    val engsmall: String? = null,
    val views: Int? = null
)