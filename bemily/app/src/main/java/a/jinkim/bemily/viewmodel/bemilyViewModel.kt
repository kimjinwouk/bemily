package a.jinkim.bemily.viewmodel


import a.jinkim.bemily.retofit.dto.response.resError
import a.jinkim.bemily.retofit.dto.response.resUserList
import a.jinkim.bemily.retofit.dto.response.userItem
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.co.tapplace.repository.bemilyRepositoryImpl
import javax.inject.Inject


@HiltViewModel
class bemilyViewModel @Inject constructor(
    private val bemilyRepositoryImpl: bemilyRepositoryImpl

) : ViewModel() {

    var _userlist = MutableLiveData<resUserList>()
    val userlist: LiveData<resUserList> get() = _userlist

    var _error = MutableLiveData<resError>()
    val error: LiveData<resError> get() = _error

    val query: MutableLiveData<String> = MutableLiveData()

    val trackList: LiveData<PagingData<userItem>>
        get() = _trackList

    private val _trackList = query.switchMap { queryString ->
        bemilyRepositoryImpl.getUserList(queryString).cachedIn(viewModelScope)
    }

    fun getUserList(q: String) {
        query.value = q
    }

}