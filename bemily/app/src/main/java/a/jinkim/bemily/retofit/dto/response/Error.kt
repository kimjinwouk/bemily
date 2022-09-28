package a.jinkim.bemily.retofit.dto.response

data class Error(
    val code: String,
    val field: String,
    val resource: String
){

    override fun toString(): String {
        return "$code, $field, $resource"
        /*
        * 오류이벤트 전달.
        * */
    }
}

