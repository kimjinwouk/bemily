package kr.co.tapplace.repository

import a.jinkim.bemily.retofit.dto.response.userInfo
import a.jinkim.bemily.retofit.dto.response.userItem
import androidx.lifecycle.LiveData
import androidx.paging.PagingData

interface bemilyRepository {

    fun getUserList(q : String) : LiveData<PagingData<userItem>>
    suspend fun getUserInfo(userId : String) :userInfo
}