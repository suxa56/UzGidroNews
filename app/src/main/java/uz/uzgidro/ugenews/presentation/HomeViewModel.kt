package uz.uzgidro.ugenews.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.uzgidro.ugenews.data.NewsRepoImpl
import uz.uzgidro.ugenews.domain.GetNewsUseCase
import uz.uzgidro.ugenews.domain.NewsModel

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = NewsRepoImpl()
    private val getNewsUseCase = GetNewsUseCase(repo)

    private val _news = MutableLiveData<List<NewsModel>>()
    val news: LiveData<List<NewsModel>> get() = _news

    fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            getNewsUseCase()?.let {
                _news.postValue(it)
            }
        }
    }
}