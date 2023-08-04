package uz.uzgidro.ugenews.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.uzgidro.ugenews.data.NewsRepoImpl
import uz.uzgidro.ugenews.domain.GetNewsUseCase

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = NewsRepoImpl()
    private val getNewsUseCase = GetNewsUseCase(repo)

    // TODO(): Place to Livedata and display in recycler view
    fun logNews() {
        viewModelScope.launch(Dispatchers.IO) {
            getNewsUseCase()?.forEach {
                Log.d("asd", it.toString())
            }
        }
    }
}