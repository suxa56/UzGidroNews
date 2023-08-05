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

    private val repo = NewsRepoImpl(application)
    private val getNewsUseCase = GetNewsUseCase(repo)

    private val _news = MutableLiveData<List<NewsModel>>()
    val news: LiveData<List<NewsModel>> get() = _news

    private val _isNetworkError = MutableLiveData<Boolean>()
    val isNetworkError: LiveData<Boolean> get() = _isNetworkError

    fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getNewsUseCase()?.let {
                    _news.postValue(it)
                }
                _isNetworkError.postValue(false)
            } catch (e: Exception) {
                _isNetworkError.postValue(true)
            }
        }
    }
}