package a.jinkim.bemily.viewmodel


import a.jinkim.bemily.retofit.dto.response.userInfo
import a.jinkim.bemily.retofit.dto.response.userItem
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.tapplace.repository.bemilyRepositoryImpl
import javax.inject.Inject


@HiltViewModel
class bemilyViewModel @Inject constructor(
    private val bemilyRepositoryImpl: bemilyRepositoryImpl

) : ViewModel() {

    val query: MutableLiveData<String> = MutableLiveData()

    val userList: LiveData<PagingData<userItem>>
        get() = _userList

    private val _userList = query.switchMap { queryString ->
        bemilyRepositoryImpl.getUserList(queryString).cachedIn(viewModelScope)
    }

    fun getUserList(q: String) {
        query.value = q
    }



    val userInfo: LiveData<userInfo>
        get() = _userInfo

    val _userInfo = MutableLiveData<userInfo>()

    fun getUserInfo(userId : String) = viewModelScope.launch{
        _userInfo.value = bemilyRepositoryImpl.getUserInfo(userId)
    }




}