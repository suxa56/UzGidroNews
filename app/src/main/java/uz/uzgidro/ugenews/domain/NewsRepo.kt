package uz.uzgidro.ugenews.domain

interface NewsRepo {
    suspend fun getNews(): List<News>?
}