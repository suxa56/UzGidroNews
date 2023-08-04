package uz.uzgidro.ugenews.data.net.dto

data class Root(
    val items: List<Item>? = null,
    val _links: Links? = null,
    val meta: Meta? = null
)
