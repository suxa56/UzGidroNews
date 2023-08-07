package uz.uzgidro.ugenews.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.text.Typography.nbsp

class NewsViewModel : ViewModel() {

    private val _textBlocks = MutableLiveData<Map<String, String>>()
    val textBlocks: LiveData<Map<String, String>> get() = _textBlocks

    fun parseText(text: String): String {
        var parsedText = text
        parsedText = parsedText
            .replace("&quot;", "\"")
            .replace("&ldquo;", "\"")
            .replace("&rdquo;", "\"")
        parsedText = parsedText
            .replace("&#39;", "'")
            .replace("&lsquo;", "'")
            .replace("&rsquo;", "'")
        parsedText = parsedText
            .replace("&ndash", "–")
        parsedText = parsedText
            .replace("&nbsp;", nbsp.toString())


        while (parsedText.indexOf("<") != -1) {
            val firstIndex = parsedText.indexOf("<")
            val lastIndex = parsedText.indexOf(">")
            parsedText = parsedText
                .replaceRange(firstIndex, lastIndex+1, "")
        }
        return parsedText
    }
}