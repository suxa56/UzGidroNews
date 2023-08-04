package uz.uzgidro.ugenews.data

import uz.uzgidro.ugenews.data.net.dto.Item
import uz.uzgidro.ugenews.data.net.dto.Root
import uz.uzgidro.ugenews.domain.NewsModel

class NewsMapper {
    private fun mapApiDtoToModel(item: Item): NewsModel {
        return NewsModel(
            id = item.id,
            title = item.uz,
            smallText = item.uzsmall,
            text = item.uztext,
            date = item.date,
            img = item.img?.replace("https://uzgidro.uz/images/news/", ""),
            views = item.views
        )
    }

    fun mapListDtoToListModel(root: Root?): List<NewsModel>? {
        return root?.items?.map {
            mapApiDtoToModel(it)
        }?.toList()
    }
}