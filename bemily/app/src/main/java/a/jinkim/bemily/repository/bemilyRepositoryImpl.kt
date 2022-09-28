package kr.co.tapplace.repository

import a.jinkim.bemily.paging.userListPagingSource
import a.jinkim.bemily.retofit.RetrofitService
import a.jinkim.bemily.retofit.RetrofitService.Companion.SEARCH_LIMIT_SIZE
import a.jinkim.bemily.retofit.dto.response.resUserList
import a.jinkim.bemily.retofit.dto.response.userItem
import androidx.lifecycle.LiveData
import androidx.paging.*
import javax.inject.Inject

class bemilyRepositoryImpl @Inject constructor(
    private val RetrofitService: RetrofitService
) : bemilyRepository {

    override fun getUserList(q : String): LiveData<PagingData<userItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                userListPagingSource(RetrofitService,q)
            }
        ).liveData
    }

}