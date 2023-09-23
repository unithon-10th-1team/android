package com.paradise.flickspick.feature.user


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
class UserDataViewModel: ViewModel() {
    enum class State { EMPTY, FULL }

    var name = ""
    var id = ""
    var passwd = ""

    private val _userNameState = MutableLiveData<State>()
    val userNameState: LiveData<State>
        get() = _userNameState

    private val _userIDState = MutableLiveData<State>()
    val userIDState: LiveData<State>
        get() = _userIDState

    private val _userPasswordState = MutableLiveData<State>()
    val userPasswordState: LiveData<State>
        get() = _userPasswordState

/*    companion object {
        const val sourceName = "LoginViewModel"
        fun create(
            application: Application,
            viewModelStoreOwner: ViewModelStoreOwner
        ): UserDataViewModel {
            return ViewModelProvider(
                viewModelStoreOwner,
                Factory(application = application)
            )[UserDataViewModel::class.java]
        }
    }*/

    //creat 함수를 뷰모델에 넣지 말고 의존성..

    fun setNameState(type: State) {
        _userNameState.value = type
    }

    fun setIDState(type: State) {
        _userIDState.value = type
    }

    fun setPassWordState(type: State) {
        _userPasswordState.value = type
    }
}