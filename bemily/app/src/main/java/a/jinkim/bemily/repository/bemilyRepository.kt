package kr.co.tapplace.repository

import a.jinkim.bemily.retofit.dto.response.resUserList
import a.jinkim.bemily.retofit.dto.response.userItem
import androidx.lifecycle.LiveData
import androidx.paging.PagingData

interface bemilyRepository {

    fun getUserList(q : String = "") : LiveData<PagingData<userItem>>

}