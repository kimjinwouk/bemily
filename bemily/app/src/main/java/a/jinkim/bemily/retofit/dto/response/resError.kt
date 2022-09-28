package a.jinkim.bemily.retofit.dto.response

data class resError(
    val documentation_url: String,
    val errors: List<Error>,
    val message: String
){
    override fun toString(): String {
        return "${errors.first().toString()}"
    }
}

