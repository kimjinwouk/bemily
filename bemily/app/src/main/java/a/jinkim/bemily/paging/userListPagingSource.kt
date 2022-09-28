package a.jinkim.bemily.paging

import a.jinkim.bemily.retofit.RetrofitService
import a.jinkim.bemily.retofit.dto.response.userItem
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

const val POST_STARTING_PAGE_INDEX = 1

class userListPagingSource(
    private val retrofitService: RetrofitService,
    private val query: String
) : PagingSource<Int, userItem>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, userItem> {
        val position = params.key ?: POST_STARTING_PAGE_INDEX

        return try {
            val response = retrofitService.getUserList(
                query = query,
                page = position
            )
            val responseData = response.items

            val prevKey = if (position == POST_STARTING_PAGE_INDEX) null else position - 1
            val nextKey = if (responseData.isEmpty()) null else position + 1

            response.total_count

            LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = nextKey
            )


        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, userItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}