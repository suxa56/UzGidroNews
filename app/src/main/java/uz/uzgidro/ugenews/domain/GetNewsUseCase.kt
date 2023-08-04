package uz.uzgidro.ugenews.domain

class GetNewsUseCase(
    private val repo: NewsRepo
) {
    suspend operator fun invoke() = repo.getNews()
}