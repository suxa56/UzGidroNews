package uz.uzgidro.ugenews.data

import uz.uzgidro.ugenews.data.net.api.RetrofitClient
import uz.uzgidro.ugenews.data.net.api.RetrofitServices
import uz.uzgidro.ugenews.domain.NewsModel
import uz.uzgidro.ugenews.domain.NewsRepo

class NewsRepoImpl : NewsRepo {
    private val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)

    private val mapper = NewsMapper()

    override suspend fun getNews(): List<NewsModel>? {
        val response = retrofitService.getNews().execute()
        return mapper.mapListDtoToListModel(
            response.body()
        )
    }

    companion object {
        private const val BASE_URL = "https://uzgidro.uz/api/"
    }
}