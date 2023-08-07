package uz.uzgidro.ugenews.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.text.Typography.nbsp

class NewsViewModel : ViewModel() {

    private val _textBlocks = MutableLiveData<Map<String, String>>()
    val textBlocks: LiveData<Map<String, String>> get() = _textBlocks

    fun parseText(text: String) {
        val blocks = mutableMapOf<String, String>()
        var mapIndex = 0
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
            val imgIndex = parsedText.indexOf("<img")
            val lastIndex = parsedText.indexOf(">")

            if (firstIndex == imgIndex) {
                blocks["$TEXT_BLOCK_PREFIX$mapIndex"] = parsedText.substringBefore("<")
                mapIndex++
                parsedText = parsedText.replaceRange(0, firstIndex + 1, "")

                val hrefIndex = parsedText.indexOf("src")
                parsedText = parsedText.replaceRange(0, hrefIndex + 5, "")

                blocks["$IMAGE_BLOCK_PREFIX$mapIndex"] = parsedText.substringBefore("\"")
                mapIndex++

                val imgCloseIndex = parsedText.indexOf(">")
                parsedText = parsedText
                    .replaceRange(0, imgCloseIndex + 1, "")
            } else {
                parsedText = parsedText
                    .replaceRange(firstIndex, lastIndex + 1, "")
            }
        }
        blocks["$TEXT_BLOCK_PREFIX$mapIndex"] = parsedText
        _textBlocks.value = blocks
    }

    companion object {
        const val TEXT_BLOCK_PREFIX = "text-"
        const val IMAGE_BLOCK_PREFIX = "img-"
    }
}