package a.jinkim.bemily.retofit

import a.jinkim.bemily.retofit.dto.response.resUserList
import a.jinkim.bemily.retofit.dto.response.userInfo
import androidx.lifecycle.LiveData
import retrofit2.http.*


interface RetrofitService {
    companion object {
        const val SEARCH_LIMIT_SIZE = 25
    }

    @GET("/search/users")
    suspend fun getUserList(
        @Query("q") query: String = "",
        @Query("page") page: Int,
        @Query("per_page") per_page: Int = SEARCH_LIMIT_SIZE,
    ): resUserList

    @GET("/users/{userId}")
    suspend fun getUserInfo(
        @Path("userId") query: String = "",
    ): userInfo
}