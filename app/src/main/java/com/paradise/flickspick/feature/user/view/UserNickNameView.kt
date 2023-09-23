package com.paradise.flickspick.feature.user.view

import android.app.Application
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.paradise.flickspick.databinding.LayoutUserNicknameBinding
import com.paradise.flickspick.feature.user.BaseFrameLayout
import com.paradise.flickspick.feature.user.UserDataViewModel

class UserNickNameView (context: Context, attrs: AttributeSet? = null) : BaseFrameLayout<LayoutUserNicknameBinding>(context, attrs) {

    private var userViewModel: UserDataViewModel
    var name = ""

    init {
        userViewModel = ViewModelProvider(context as ViewModelStoreOwner)[UserDataViewModel::class.java]

        vb.userNameField.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            //값 변경 시 실행되는 함수
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                name = vb.userNameField.text.toString()
                //입력값 담기
                userViewModel.name = vb.userNameField.text.toString()

                if(name.isNotEmpty()){
                    userViewModel.setNameState(UserDataViewModel.State.FULL)
                }else{
                    userViewModel.setNameState(UserDataViewModel.State.EMPTY)
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }
}