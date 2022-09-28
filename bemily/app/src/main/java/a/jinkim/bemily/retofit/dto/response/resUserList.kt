package a.jinkim.bemily.retofit.dto.response

data class resUserList(
    val incomplete_results: Boolean,
    val items: List<userItem>,
    val total_count: Int
)