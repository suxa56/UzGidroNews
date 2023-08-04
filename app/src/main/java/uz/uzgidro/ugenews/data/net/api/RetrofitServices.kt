package uz.uzgidro.ugenews.data.net.api

import retrofit2.Call
import retrofit2.http.GET
import uz.uzgidro.ugenews.data.net.dto.Root

interface RetrofitServices {
    @GET("news")
    fun getNews(): Call<Root>
}